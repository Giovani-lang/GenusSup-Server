package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Etudiant;
import com.genus.GENUS_SUP.Entity.FicheDePresence;
import com.genus.GENUS_SUP.Entity.Planification;
import com.genus.GENUS_SUP.Mapper.FicheDePresenceMapper;
import com.genus.GENUS_SUP.dto.ficheDePresence_dto.FicheDePresenceRequest;
import com.genus.GENUS_SUP.dto.ficheDePresence_dto.FicheDePresenceResponse;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Repository.EtudiantRepository;
import com.genus.GENUS_SUP.Repository.FicheDePresenceRepository;
import com.genus.GENUS_SUP.Repository.PlanificationRepository;
import com.genus.GENUS_SUP.Service.Interface.IFicheDePresenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FicheDePresenceServiceImpl implements IFicheDePresenceService {
    private final FicheDePresenceRepository ficheDePresenceRepo;
    private final FicheDePresenceMapper ficheDePresenceMapper;
    private final PlanificationRepository lemploiRepo;
    private final EtudiantRepository etudiantRepo;


    @Override
    public List<FicheDePresenceResponse> addFicheDePresence(List<FicheDePresenceRequest> ficheDePresence) throws RessourceExistException {
        List<FicheDePresence> fiches = new ArrayList<>();
        for (FicheDePresenceRequest request : ficheDePresence) {
        FicheDePresence fiche = new FicheDePresence();
            Etudiant etudiant = this.etudiantRepo.findById(request.getEtudiantId())
                    .orElseThrow(()-> new RessourceNotFoundException("This ID :"+request.getEtudiantId()+"doesn't exist"));
            fiche.setEtudiant(etudiant);
            Planification planification = this.lemploiRepo.findById(request.getPlanificationId())
                    .orElseThrow(()-> new RessourceNotFoundException("This ID :"+request.getPlanificationId()+"doesn't exist"));
            fiche.setPlanification(planification);
            fiche.setAbsent(request.isAbsent());
        fiches.add(fiche);
        }
        return this.ficheDePresenceMapper.fromFicheDePresence(ficheDePresenceRepo.saveAll(fiches));
    }

    @Override
    public FicheDePresenceResponse addJustification(FicheDePresenceRequest ficheDePresence) {
        FicheDePresence fiche = this.ficheDePresenceMapper.fromFicheDePresenceResquest(ficheDePresence);
        Etudiant etudiant = this.etudiantRepo.findById(ficheDePresence.getEtudiantId())
                .orElseThrow(()-> new RessourceNotFoundException("This ID :"+ficheDePresence.getEtudiantId()+"doesn't exist"));
        fiche.setEtudiant(etudiant);
        Planification planification = this.lemploiRepo.findById(ficheDePresence.getPlanificationId())
                .orElseThrow(()-> new RessourceNotFoundException("This ID :"+ficheDePresence.getPlanificationId()+"doesn't exist"));
        fiche.setPlanification(planification);
        fiche.setAbsent(ficheDePresence.isAbsent());
        return this.ficheDePresenceMapper.toFicheDePresence(this.ficheDePresenceRepo.save(fiche));
    }

    @Override
    public List<FicheDePresenceResponse> getFichesDePresence(Long ecoleId) {
        List<FicheDePresence> fiches = this.ficheDePresenceRepo.fecthAll(ecoleId);
        return this.ficheDePresenceMapper.fromFicheDePresence(fiches);
    }
    @Override
    public List<FicheDePresenceResponse> getEtudiantList(String matricule) {
        List<FicheDePresence> fiches = this.ficheDePresenceRepo.findByEtudiant(matricule);
        return this.ficheDePresenceMapper.fromFicheDePresence(fiches);
    }

}
