package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Cycle;
import com.logonedigital.PI.SCHULE.Entity.Ecole;
import com.logonedigital.PI.SCHULE.Entity.Filiere;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.FiliereMapper;
import com.logonedigital.PI.SCHULE.Repository.CycleRepository;
import com.logonedigital.PI.SCHULE.Repository.EcoleRepository;
import com.logonedigital.PI.SCHULE.Repository.FiliereRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IFiliereService;
import com.logonedigital.PI.SCHULE.dto.filiere_dto.FiliereRequest;
import com.logonedigital.PI.SCHULE.dto.filiere_dto.FiliereResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FiliereServiceImpl implements IFiliereService {

    private final FiliereRepository filiereRepo;
    private final FiliereMapper filiereMapper;
    private final CycleRepository cycleRepo;
    @Override
    public FiliereResponse addFiliere(FiliereRequest filiereRequest) {
        Filiere filiere = this.filiereMapper.fromFiliereRequest(filiereRequest);
        Cycle cycle = this.cycleRepo.findById(filiereRequest.getCycleId())
                .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
        filiere.setCycle(cycle);
        return this.filiereMapper.fromFiliere(this.filiereRepo.save(filiere));
    }

    @Override
    public List<FiliereResponse> getFiliere(Long ecoleId) {
        List<Filiere> filieres = this.filiereRepo.findAllByEcole(ecoleId);
        List<FiliereResponse> filiereResponses = new ArrayList<>();
        filieres.forEach(filiere -> filiereResponses.add(this.filiereMapper.fromFiliere(filiere)));
        return filiereResponses;
    }

    @Override
    public List<FiliereResponse> getFiliereByCycle(Long cycleId) {
        List<Filiere> filieres = this.filiereRepo.findByCycle(cycleId);
        List<FiliereResponse> filiereResponses = new ArrayList<>();
        filieres.forEach(filiere -> filiereResponses.add(this.filiereMapper.fromFiliere(filiere)));
        return filiereResponses;
    }

    @Override
    public FiliereResponse getByName(String nom) {
        return this.filiereMapper.fromFiliere(this.filiereRepo.findByNom(nom).get());
    }

    @Override
    public FiliereResponse updateFiliere(Long id, FiliereRequest filiereRequest) {

       try{
           Filiere newFiliere = this.filiereRepo.findById(id).get();
           Filiere filiere = this.filiereMapper.fromFiliereRequest(filiereRequest);
           Cycle cycle = this.cycleRepo.findById(filiereRequest.getCycleId())
                   .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
           newFiliere.setCycle(cycle);
           newFiliere.setNom(filiere.getNom());
           return this.filiereMapper.fromFiliere(this.filiereRepo.saveAndFlush(newFiliere));
       } catch (NoSuchElementException e){
           throw new RessourceNotFoundException("Not found");
       }
    }

}
