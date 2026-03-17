package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Emargement;
import com.genus.GENUS_SUP.Entity.Enseignant;
import com.genus.GENUS_SUP.Entity.Planification;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.EmargementMapper;
import com.genus.GENUS_SUP.Repository.EmargementRepository;
import com.genus.GENUS_SUP.Repository.EnseignantRepository;
import com.genus.GENUS_SUP.Repository.PlanificationRepository;
import com.genus.GENUS_SUP.Service.Interface.IEmargementService;
import com.genus.GENUS_SUP.dto.emargement_dto.EmargementRequest;
import com.genus.GENUS_SUP.dto.emargement_dto.EmargementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmargementServiceImpl implements IEmargementService {
    private final EmargementRepository emargementRepo;
    private final EmargementMapper emargementMapper;
    private final PlanificationRepository planificationRepo;
    private final EnseignantRepository enseignantRepo;
    @Override
    public EmargementResponse addEmargement(EmargementRequest emargementRequest) {
        Optional<Emargement> planChecking = this.emargementRepo.findByPlanning(emargementRequest.getPlanificationId());
        if (planChecking.isPresent()){
            throw new RessourceExistException("Already exist");
        }
        Emargement emargement = this.emargementMapper.fromEmargementRequest(emargementRequest);
        Enseignant enseignant = this.enseignantRepo.findById(emargementRequest.getEnseignantId())
                .orElseThrow(()-> new RessourceNotFoundException("This ID :"+emargementRequest.getEnseignantId()+"doesn't exist"));
        emargement.setEnseignant(enseignant);
        Planification planification = this.planificationRepo.findById(emargementRequest.getPlanificationId())
                .orElseThrow(()-> new RessourceNotFoundException("This ID :"+emargementRequest.getPlanificationId()+"doesn't exist"));
        emargement.setPlanification(planification);
        planification.setStatus("Complete");
        emargement.setPresent(emargementRequest.isPresent());
        return this.emargementMapper.fromEmargement(this.emargementRepo.save(emargement));
    }

    @Override
    public List<EmargementResponse> getAllEmargement(Long ecoleId) {
        List<Emargement> emargements = this.emargementRepo.AllEmargements(ecoleId);
        List<EmargementResponse> emargementResponses = new ArrayList<>();
        emargements.forEach(emargement ->emargementResponses.add(this.emargementMapper.fromEmargement(emargement)));
        return emargementResponses;
    }

    @Override
    public List<EmargementResponse> getEnseignantList(String email) {
        List<Emargement> emargements = this.emargementRepo.findByEnseignant(email);
        List<EmargementResponse> emargementResponses = new ArrayList<>();
        emargements.forEach(emargement ->emargementResponses.add(this.emargementMapper.fromEmargement(emargement)));
        return emargementResponses;
    }
}
