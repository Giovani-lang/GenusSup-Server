package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.AnneeAcademique;
import com.genus.GENUS_PRIMO.Entity.Matiere;
import com.genus.GENUS_PRIMO.Entity.Planification;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Mapper.PlanificationMapper;
import com.genus.GENUS_PRIMO.Repository.AnneeAcademiqueRepository;
import com.genus.GENUS_PRIMO.Repository.MatiereRepository;
import com.genus.GENUS_PRIMO.Repository.PlanificationRepository;
import com.genus.GENUS_PRIMO.Service.Interface.IPlanificationService;
import com.genus.GENUS_PRIMO.dto.planification_dto.PlanificationRequest;
import com.genus.GENUS_PRIMO.dto.planification_dto.PlanificationResponse;
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
    private final EmailService emailService;

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


        Planification planificationSaved = this.planificationRepo.save(planification);
        this.emailService.sendUserNotificationEmail(planificationSaved.getMatiere().getEnseignant());
        return this.planificationMapper.fromPlanification(planificationSaved);
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

        Planification planificationEdited = this.planificationRepo.save(planificationUpdated);
        this.emailService.sendUserNotificationEmail(planificationEdited.getMatiere().getEnseignant());
        return this.planificationMapper.fromPlanification(planificationEdited);    }

    @Override
    public void deletePlanning(Long id) {
        this.planificationRepo.deleteById(id);
    }
}
