package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.Campus;
import com.genus.GENUS_PRIMO.Entity.Cycle;
import com.genus.GENUS_PRIMO.Entity.Ecole;
import com.genus.GENUS_PRIMO.Mapper.CycleMapper;
import com.genus.GENUS_PRIMO.Repository.CampusRepository;
import com.genus.GENUS_PRIMO.dto.cycle_dto.CycleRequest;
import com.genus.GENUS_PRIMO.dto.cycle_dto.CycleResponse;
import com.genus.GENUS_PRIMO.Exception.RessourceExistException;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Repository.CycleRepository;
import com.genus.GENUS_PRIMO.Repository.EcoleRepository;
import com.genus.GENUS_PRIMO.Service.Interface.ICycleService;
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
    private final CampusRepository campusRepo;

    @Override
    public CycleResponse addCycle(CycleRequest cycleRequest) {
        Cycle cycle = this.cycleMapper.fromCycleRequest(cycleRequest);
        Optional<Cycle> cycleAsked = this.cycleRepo.getCycle(cycleRequest.getCampusId(), cycleRequest.getNom());
        if (cycleAsked.isPresent()){
            throw new RessourceExistException("Cycle already exist !!!");
        }
        Campus campus = this.campusRepo.findById(cycleRequest.getCampusId())
                .orElseThrow(()->new RessourceNotFoundException("Not found, try again"));
        cycle.setCampus(campus);
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
            Optional<Cycle> cycleAsked = this.cycleRepo.getCycle(cycleRequest.getCampusId(), cycleRequest.getNom());
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
