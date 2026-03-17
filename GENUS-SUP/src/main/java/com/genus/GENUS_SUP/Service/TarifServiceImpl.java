package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Option;
import com.genus.GENUS_SUP.Entity.Tarif;
import com.genus.GENUS_SUP.Entity.Tranche;
import com.genus.GENUS_SUP.Mapper.TarifMapper;
import com.genus.GENUS_SUP.dto.tarif_dto.TarifRequest;
import com.genus.GENUS_SUP.dto.tarif_dto.TarifResponse;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Repository.OptionRepository;
import com.genus.GENUS_SUP.Repository.TarifRepository;
import com.genus.GENUS_SUP.Service.Interface.ITarifService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarifServiceImpl implements ITarifService {

    private final TarifRepository tarifRepo;
    private final OptionRepository optionRepo;
    private final TarifMapper tarifMapper;
    @Override
    public TarifResponse addTarif(TarifRequest tarif) {
        Tarif tarifSaved = this.tarifMapper.fromTarifRequest(tarif);
        Optional<Tarif> checkTarif = this.tarifRepo.getTarif(tarif.getOptionId());
        if(checkTarif.isPresent()){
            throw new RessourceExistException("This option have already a tariff");
        }
        List<Tranche> tranches = tarif.getTranches().stream().map(dto -> {
            Tranche tranche = new Tranche();
            tranche.setNumero(dto.getNumero());
            tranche.setMontant(dto.getMontant());
            tranche.setDate(dto.getDate());
            tranche.setTarif(tarifSaved);
            return tranche;
        }).collect(Collectors.toList());

        tarifSaved.setTranches(tranches);
        Option option = this.optionRepo.findById(tarif.getOptionId())
                .orElseThrow(()-> new RessourceNotFoundException("This option doesn't exist"));
        tarifSaved.setOption(option);
        return this.tarifMapper.fromTarif(tarifRepo.save(tarifSaved));
    }

    @Override
    public List<TarifResponse> getAll(Long ecoleId) {
        List<TarifResponse> tarifResponses = new ArrayList<>();
        List<Tarif> tarifs = this.tarifRepo.findAllByEcole(ecoleId);
        tarifs.forEach(tarif->tarifResponses.add(this.tarifMapper.fromTarif(tarif)));
        return tarifResponses;
    }

    @Override
    public TarifResponse editTarif(Long id, TarifRequest tarif) {
        try {
            // Récupération de l'entité Tarif existante
            Tarif tarifEdited = this.tarifRepo.findById(id)
                    .orElseThrow(() -> new RessourceNotFoundException("Tariff not found with id " + id));

            // Récupération des tranches existantes
            List<Tranche> existingTranches = tarifEdited.getTranches();
            Map<Long, Tranche> existingTranchesMap = existingTranches.stream()
                    .collect(Collectors.toMap(Tranche::getNumero, Function.identity()));

            // Mise à jour ou ajout des nouvelles tranches
            List<Tranche> updatedTranches = tarif.getTranches().stream().map(dto -> {
                Tranche tranche = existingTranchesMap.getOrDefault(dto.getNumero(), new Tranche());
                tranche.setNumero(dto.getNumero());
                tranche.setMontant(dto.getMontant());
                tranche.setDate(dto.getDate());
                tranche.setTarif(tarifEdited);
                return tranche;
            }).collect(Collectors.toList());

            // Supprimer les tranches qui ne sont plus présentes dans la nouvelle liste
            List<Tranche> tranchesToRemove = existingTranches.stream()
                    .filter(tranche -> tarif.getTranches().stream().noneMatch(dto -> dto.getNumero().equals(tranche.getNumero())))
                    .collect(Collectors.toList());
            tranchesToRemove.forEach(tranche -> tranche.setTarif(null));

            tarifEdited.getTranches().clear();
            tarifEdited.getTranches().addAll(updatedTranches);

            // Mise à jour de l'option
            Option option = this.optionRepo.findById(tarif.getOptionId())
                    .orElseThrow(() -> new RessourceNotFoundException("This option doesn't exist"));
            tarifEdited.setOption(option);

            // Mise à jour du montant
            tarifEdited.setMontant(tarif.getMontant());

            // Sauvegarde et retour de l'entité mise à jour
            return this.tarifMapper.fromTarif(this.tarifRepo.saveAndFlush(tarifEdited));
        } catch (NoSuchElementException ex) {
            throw new RessourceNotFoundException("Impossible, try again!");
        }
    }

    @Override
    public TarifResponse deleteTarif(Long id) {
       try{
           Tarif tarifEdited= this.tarifRepo.findById(id).get();
           tarifEdited.setDeleted(true);
           return this.tarifMapper.fromTarif(this.tarifRepo.saveAndFlush(tarifEdited));
       }catch (Exception ex){
           throw new RessourceNotFoundException("Impossible to delete this tariff");
       }
    }

}
