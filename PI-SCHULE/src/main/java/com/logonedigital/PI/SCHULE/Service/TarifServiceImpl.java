package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Option;
import com.logonedigital.PI.SCHULE.Entity.Tarif;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.TarifMapper;
import com.logonedigital.PI.SCHULE.Repository.OptionRepository;
import com.logonedigital.PI.SCHULE.Repository.TarifRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.ITarifService;
import com.logonedigital.PI.SCHULE.dto.tarif_dto.TarifRequest;
import com.logonedigital.PI.SCHULE.dto.tarif_dto.TarifResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TarifServiceImpl implements ITarifService {

    private final TarifRepository tarifRepo;
    private final OptionRepository optionRepo;
    private final TarifMapper tarifMapper;
    @Override
    public TarifResponse addTarif(TarifRequest tarif) {
        Tarif tarifSaved = this.tarifMapper.fromTarifRequest(tarif);
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
           Option option = this.optionRepo.findById(tarif.getOptionId())
                   .orElseThrow(()-> new RessourceNotFoundException("This option doesn't exist"));
           tarifEdited.setOption(option);
           tarifEdited.setMontant(tarif.getMontant());
           return this.tarifMapper.fromTarif(this.tarifRepo.saveAndFlush(tarifEdited));
       }catch (Exception ex){
           throw new RessourceNotFoundException("Impossible, try again!");
       }
    }

}
