package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.Option;
import com.genus.GENUS_PRIMO.Entity.Tarif;
import com.genus.GENUS_PRIMO.Mapper.TarifMapper;
import com.genus.GENUS_PRIMO.dto.tarif_dto.TarifRequest;
import com.genus.GENUS_PRIMO.dto.tarif_dto.TarifResponse;
import com.genus.GENUS_PRIMO.Exception.RessourceExistException;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Repository.OptionRepository;
import com.genus.GENUS_PRIMO.Repository.TarifRepository;
import com.genus.GENUS_PRIMO.Service.Interface.ITarifService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TarifServiceImpl implements ITarifService {

    private final TarifRepository tarifRepo;
    private final OptionRepository optionRepo;
    private final TarifMapper tarifMapper;
    @Override
    public TarifResponse addTarif(TarifRequest tarif) {
        Tarif tarifSaved = this.tarifMapper.fromTarifRequest(tarif);
        Optional<Tarif> tarifAsked = this.tarifRepo.getTarif( tarif.getOptionId());
        if (tarifAsked.isPresent()){
            throw new RessourceExistException("Tarif already exist !!!");
        }
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
       try{
           Tarif tarifEdited= this.tarifRepo.findById(id).get();
           Optional<Tarif> tarifAsked = this.tarifRepo.getTarif( tarif.getOptionId());
           if (tarifAsked.isPresent()){
               throw new RessourceExistException("Tarif already exist !!!");
           }
           Option option = this.optionRepo.findById(tarif.getOptionId())
                   .orElseThrow(()-> new RessourceNotFoundException("This option doesn't exist"));
           tarifEdited.setOption(option);
           tarifEdited.setMontant(tarif.getMontant());
           return this.tarifMapper.fromTarif(this.tarifRepo.saveAndFlush(tarifEdited));
       }catch (NoSuchElementException ex){
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
           throw new RessourceNotFoundException("Impossible to delete this tarif");
       }
    }

}
