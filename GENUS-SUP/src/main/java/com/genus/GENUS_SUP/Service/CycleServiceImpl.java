package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Cycle;
import com.genus.GENUS_SUP.Entity.Departement;
import com.genus.GENUS_SUP.Mapper.CycleMapper;
import com.genus.GENUS_SUP.Repository.DepartementRepository;
import com.genus.GENUS_SUP.dto.cycle_dto.CycleRequest;
import com.genus.GENUS_SUP.dto.cycle_dto.CycleResponse;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Repository.CycleRepository;
import com.genus.GENUS_SUP.Service.Interface.ICycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CycleServiceImpl implements ICycleService {
    private final CycleRepository cycleRepo;
    private final CycleMapper cycleMapper;
    private final DepartementRepository departementRepo;

    @Override
    public CycleResponse addCycle(CycleRequest cycleRequest) {
        Cycle cycle = this.cycleMapper.fromCycleRequest(cycleRequest);
        Optional<Cycle> cycleAsked = this.cycleRepo.getCycle(cycleRequest.getDepartementId(), cycleRequest.getNom());
        if (cycleAsked.isPresent()){
            throw new RessourceExistException("Cycle already exist !!!");
        }
        Departement departement = this.departementRepo.findById(cycleRequest.getDepartementId())
                .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
        cycle.setDepartement(departement);
        return this.cycleMapper.fromCycle(this.cycleRepo.save(cycle));
    }

    @Override
    public List<CycleResponse> getCycles(Long id) {
        List<Cycle> cycles = this.cycleRepo.findAllByEcole(id);
        List<CycleResponse> cycleResponses = new ArrayList<>();
        cycles.forEach(cycle -> cycleResponses.add(this.cycleMapper.fromCycle(cycle)));
        return cycleResponses;
    }

    @Override
    public CycleResponse editCycle(Long id, CycleRequest cycleRequest) {
        try{
            Cycle cycle = this.cycleMapper.fromCycleRequest(cycleRequest);
            Cycle cycleUpdated = this.cycleRepo.findById(id).get();
            Optional<Cycle> cycleAsked = this.cycleRepo.getCycle(cycleRequest.getDepartementId(), cycleRequest.getNom());
            if (cycleAsked.isPresent()){
                throw new RessourceExistException("Cycle already exist !!!");
            }
            Departement departement = this.departementRepo.findById(cycleRequest.getDepartementId())
                    .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
            cycleUpdated.setDepartement(departement);
            cycleUpdated.setNom(cycle.getNom());
            cycleUpdated.setSection(cycle.getSection());
            return this.cycleMapper.fromCycle(this.cycleRepo.saveAndFlush(cycleUpdated));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("Not found");
        }
    }
    @Override
    public CycleResponse deleteCycle(Long id) {
        try{
            Cycle cycleUpdated = this.cycleRepo.findById(id).get();
            cycleUpdated.setDeleted(true);
            return this.cycleMapper.fromCycle(this.cycleRepo.saveAndFlush(cycleUpdated));
        }catch (NoSuchElementException e){
            throw new RessourceNotFoundException("Impossible to delete this cycle");
        }
    }

}
