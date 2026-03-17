package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Cycle;
import com.genus.GENUS_SUP.Entity.Departement;
import com.genus.GENUS_SUP.Entity.Filiere;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.CycleMapper;
import com.genus.GENUS_SUP.Mapper.FiliereMapper;
import com.genus.GENUS_SUP.Repository.CycleRepository;
import com.genus.GENUS_SUP.Repository.DepartementRepository;
import com.genus.GENUS_SUP.Repository.FiliereRepository;
import com.genus.GENUS_SUP.Service.Interface.ICycleService;
import com.genus.GENUS_SUP.Service.Interface.IFiliereService;
import com.genus.GENUS_SUP.dto.cycle_dto.CycleRequest;
import com.genus.GENUS_SUP.dto.cycle_dto.CycleResponse;
import com.genus.GENUS_SUP.dto.filiere_dto.FiliereRequest;
import com.genus.GENUS_SUP.dto.filiere_dto.FiliereResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FiliereServiceImpl implements IFiliereService {
    private final FiliereRepository filiereRepo;
    private final FiliereMapper filiereMapper;
    private final CycleRepository cycleRepo;

    @Override
    public FiliereResponse addFiliere(FiliereRequest filiereRequest) {
        Filiere filiere = this.filiereMapper.fromFiliereRequest(filiereRequest);
        Optional<Filiere> filiereAsked = this.filiereRepo.getFiliere(filiereRequest.getCycleId(), filiereRequest.getNom());
        if (filiereAsked.isPresent()){
            throw new RessourceExistException("Filiere already exist !!!");
        }
        Cycle cycle = this.cycleRepo.findById(filiereRequest.getCycleId())
                .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
        filiere.setCycle(cycle);
        return this.filiereMapper.fromFiliere(this.filiereRepo.save(filiere));
    }

    @Override
    public List<FiliereResponse> getFiliere(Long id) {
        List<Filiere> filieres = this.filiereRepo.findAllByEcole(id);
        List<FiliereResponse> filiereResponses = new ArrayList<>();
        filieres.forEach(filiere -> filiereResponses.add(this.filiereMapper.fromFiliere(filiere)));
        return filiereResponses;
    }

    @Override
    public FiliereResponse editFiliere(Long id, FiliereRequest filiereRequest) {
        try{
            Filiere filiere = this.filiereMapper.fromFiliereRequest(filiereRequest);
            Filiere filiereUpdated = this.filiereRepo.findById(id).get();
            Optional<Filiere> filiereAsked = this.filiereRepo.getFiliere(filiereRequest.getCycleId(), filiereRequest.getNom());
            if (filiereAsked.isPresent()){
                throw new RessourceExistException("Cycle already exist !!!");
            }
            Cycle cycle = this.cycleRepo.findById(filiereRequest.getCycleId())
                    .orElseThrow(() -> new RessourceNotFoundException("This cycle doesn't exist"));
            filiereUpdated.setNom(filiere.getNom());
            filiereUpdated.setCycle(cycle);
            return this.filiereMapper.fromFiliere(this.filiereRepo.saveAndFlush(filiereUpdated));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("Not found");
        }
    }
    @Override
    public FiliereResponse deleteFiliere(Long id) {
        try{
            Filiere filiere = this.filiereRepo.findById(id).get();
            filiere.setDeleted(true);
            return this.filiereMapper.fromFiliere(this.filiereRepo.saveAndFlush(filiere));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("Impossible to delete this filiere");
        }
    }

}
