package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Cycle;
import com.logonedigital.PI.SCHULE.Entity.Ecole;
import com.logonedigital.PI.SCHULE.Entity.SuperAdmin;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.CycleMapper;
import com.logonedigital.PI.SCHULE.Repository.CycleRepository;
import com.logonedigital.PI.SCHULE.Repository.EcoleRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.ICycleService;
import com.logonedigital.PI.SCHULE.dto.cycle_dto.CycleRequest;
import com.logonedigital.PI.SCHULE.dto.cycle_dto.CycleResponse;
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
    private final EcoleRepository ecoleRepo;

    @Override
    public CycleResponse addCycle(CycleRequest cycleRequest) {
        Cycle cycle = this.cycleMapper.fromCycleRequest(cycleRequest);
        Optional<Cycle> cycleAsked = this.cycleRepo.getCycle(cycleRequest.getEcoleId(), cycleRequest.getNom());
        if (cycleAsked.isPresent()){
            throw new RessourceExistException("Cycle already exist !!!");
        }
        Ecole ecole = this.ecoleRepo.findById(cycleRequest.getEcoleId())
                .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
        cycle.setEcole(ecole);
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
            Optional<Cycle> cycleAsked = this.cycleRepo.getCycle(cycleRequest.getEcoleId(), cycleRequest.getNom());
            if (cycleAsked.isPresent()){
                throw new RessourceExistException("Cycle already exist !!!");
            }
            Cycle cycleUpdated = this.cycleRepo.findById(id).get();
            cycleUpdated.setNom(cycle.getNom());
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
