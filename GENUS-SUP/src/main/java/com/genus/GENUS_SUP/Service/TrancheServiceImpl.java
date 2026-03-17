package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Tranche;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.TrancheMapper;
import com.genus.GENUS_SUP.Repository.TrancheRepository;
import com.genus.GENUS_SUP.Service.Interface.ITrancheService;
import com.genus.GENUS_SUP.dto.tranche_dto.TrancheRequest;
import com.genus.GENUS_SUP.dto.tranche_dto.TrancheResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TrancheServiceImpl implements ITrancheService {

    private final TrancheRepository trancheRepo;
    private final TrancheMapper trancheMapper;
    @Override
    public TrancheResponse addTranche(TrancheRequest trancheRequest) {
        Tranche tranche = this.trancheMapper.fromTrancheRequest(trancheRequest);
        return this.trancheMapper.fromTranche(this.trancheRepo.save(tranche));
    }

    @Override
    public TrancheResponse editTranche(Long id, TrancheRequest trancheRequest) throws RessourceNotFoundException {
        try{
        Tranche trancheUpdated = this.trancheRepo.findById(id).get();
        Tranche tranche = this.trancheMapper.fromTrancheRequest(trancheRequest);
        trancheUpdated.setDate(tranche.getDate());
        trancheUpdated.setMontant(tranche.getMontant());
        trancheUpdated.setNumero(tranche.getNumero());
        return this.trancheMapper.fromTranche(this.trancheRepo.save(trancheUpdated));
        }catch (NoSuchElementException ex){
            throw new RessourceNotFoundException("Tranche not found");
        }
    }

    @Override
    public List<TrancheResponse> search(Long tarifId) {
        List<TrancheResponse> trancheResponses = new ArrayList<>();
        List<Tranche> tranches = this.trancheRepo.getAllTrancheByTariff(tarifId);

        tranches.forEach(tranche->trancheResponses.add(this.trancheMapper.fromTranche(tranche)));
        return trancheResponses;
    }
}
