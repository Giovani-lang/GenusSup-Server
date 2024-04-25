package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Etudiant;
import com.logonedigital.PI.SCHULE.Entity.FicheDePresence;
import com.logonedigital.PI.SCHULE.Entity.Matiere;
import com.logonedigital.PI.SCHULE.Entity.Planification;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.FicheDePresenceMapper;
import com.logonedigital.PI.SCHULE.Repository.EtudiantRepository;
import com.logonedigital.PI.SCHULE.Repository.FicheDePresenceRepository;
import com.logonedigital.PI.SCHULE.Repository.PlanificationRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IFicheDePresenceService;
import com.logonedigital.PI.SCHULE.dto.ficheDePresence_dto.FicheDePresenceRequest;
import com.logonedigital.PI.SCHULE.dto.ficheDePresence_dto.FicheDePresenceResponse;
import com.logonedigital.PI.SCHULE.dto.ficheDePresence_dto.FicheDePresenceUpdated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
    public List<FicheDePresenceResponse> getEtudiantList(String email) {
        List<FicheDePresence> fiches = this.ficheDePresenceRepo.findByEtudiant(email);
        return this.ficheDePresenceMapper.fromFicheDePresence(fiches);
    }

    @Override
    public List<FicheDePresenceResponse> updateFicheDePresence(List<FicheDePresenceUpdated> ficheDePresence) {
        try {
            List<FicheDePresence> fiches = new ArrayList<>();
                for (FicheDePresenceUpdated request : ficheDePresence) {
                    FicheDePresence newFicheDePresence = this.ficheDePresenceRepo.findById(request.getId())
                            .orElseThrow(() -> new RessourceNotFoundException("This ID :" + request.getId() + " doesn't exist"));
                    newFicheDePresence.setAbsent(request.isAbsent());
                    fiches.add(newFicheDePresence);
                }
            return this.ficheDePresenceMapper.fromFicheDePresence(this.ficheDePresenceRepo.saveAll(fiches));
        }catch (NoSuchElementException ex){
            throw new RessourceNotFoundException("An error has occurred while retrieving the data, and the modification cannot be made");
        }
    }
}
