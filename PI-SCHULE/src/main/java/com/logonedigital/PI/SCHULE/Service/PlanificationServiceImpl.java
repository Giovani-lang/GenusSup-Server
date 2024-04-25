package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.AnneeAcademique;
import com.logonedigital.PI.SCHULE.Entity.Planification;
import com.logonedigital.PI.SCHULE.Entity.Matiere;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.PlanificationMapper;
import com.logonedigital.PI.SCHULE.Repository.AnneeAcademiqueRepository;
import com.logonedigital.PI.SCHULE.Repository.PlanificationRepository;
import com.logonedigital.PI.SCHULE.Repository.MatiereRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IPlanificationService;
import com.logonedigital.PI.SCHULE.dto.planification_dto.PlanificationRequest;
import com.logonedigital.PI.SCHULE.dto.planification_dto.PlanificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanificationServiceImpl implements IPlanificationService {
    private final PlanificationRepository planificationRepo;
    private final PlanificationMapper planificationMapper;
    private final MatiereRepository matiereRepo;
    private final AnneeAcademiqueRepository anneeAcademiqueRepo;
    @Override
    public PlanificationResponse addPlanning(PlanificationRequest planificationRequest) {
        Planification planification = this.planificationMapper.fromPlanificationRequest(planificationRequest);
        Matiere matiere = this.matiereRepo.findById(planificationRequest.getMatiereId())
                        .orElseThrow(()-> new RessourceNotFoundException("This matiere doesn't exist"));
        planification.setMatiere(matiere);
        AnneeAcademique anneeAcademique = this.anneeAcademiqueRepo.findById(planificationRequest.getAnneeAcademiqueId())
                .orElseThrow(()-> new RessourceNotFoundException("This year doesn't exist"));
        planification.setAnneeAcademique(anneeAcademique);

        //formatage de l'heure de debut en type localTime;
        LocalTime heure = LocalTime.parse(planificationRequest.getDebut());
        //Addition de l'heure de debut avec la duree pour avoir l'heure de fin;
        LocalTime resultat = heure.plusHours(planificationRequest.getDuree());
        //Enregistrement de la date de fin dans sa variable en reconversion vers le type string;
        planification.setFin(resultat.toString());

        return this.planificationMapper.fromPlanification(this.planificationRepo.save(planification));
    }

    @Override
    public List<PlanificationResponse> findAllPlanning() {
        List<Planification> planifications =  (List<Planification>) this.planificationRepo.findAll();
        List<PlanificationResponse> planificationRespons = new ArrayList<>();
        planifications.forEach(planification -> planificationRespons.add(this.planificationMapper
                .fromPlanification(planification)));
        return planificationRespons;
    }

    @Override
    public List<PlanificationResponse> findAllPlanningByOption(Long ecoleId,Long optionId) {
        List<Planification> planifications =  (List<Planification>) this.planificationRepo.findByOption(ecoleId, optionId);
        List<PlanificationResponse> planificationRespons = new ArrayList<>();
        planifications.forEach(planification -> planificationRespons.add(this.planificationMapper
                .fromPlanification(planification)));
        return planificationRespons;
    }

    @Override
    public List<PlanificationResponse> findAllPlanningForTeacher(Long ecoleId, String ensEmail) {
        List<Planification> planifications =  (List<Planification>) this.planificationRepo.findByTeacher(ecoleId, ensEmail);
        List<PlanificationResponse> planificationRespons = new ArrayList<>();
        planifications.forEach(planification -> planificationRespons.add(this.planificationMapper
                .fromPlanification(planification)));
        return planificationRespons;
    }


    @Override
    public PlanificationResponse updatePlanning(Long id, PlanificationRequest planificationRequest) {

        Planification planificationUpdated = this.planificationRepo.findById(id).get();
        Planification planification = this.planificationMapper.fromPlanificationRequest(planificationRequest);

        planificationUpdated.setJour(planification.getJour());
        planificationUpdated.setDebut(planification.getDebut());
        //formatage de l'heure de debut en type localTime;
        LocalTime heure = LocalTime.parse(planification.getDebut());
        //Addition de l'heure de debut avec la duree pour avoir l'heure de fin;
        LocalTime resultat = heure.plusHours(planification.getDuree());
        //Enregistrement de la date de fin dans sa variable en reconversion vers le type string;
        planificationUpdated.setFin(resultat.toString());
        planificationUpdated.setDuree(planification.getDuree());

        Matiere matiere = this.matiereRepo.findById(planificationRequest.getMatiereId())
                .orElseThrow(()-> new RessourceNotFoundException("This matiere doesn't exist"));
        planificationUpdated.setMatiere(matiere);
        AnneeAcademique anneeAcademique = this.anneeAcademiqueRepo.findById(planificationRequest.getAnneeAcademiqueId())
                .orElseThrow(()-> new RessourceNotFoundException("This year doesn't exist"));
        planificationUpdated.setAnneeAcademique(anneeAcademique);
        planificationUpdated.setSalle(planification.getSalle());

        return this.planificationMapper.fromPlanification(this.planificationRepo.saveAndFlush(planificationUpdated));
    }

    @Override
    public void deletePlanning(Long id) {
        this.planificationRepo.deleteById(id);
    }
}
