package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.AnneeAcademique;
import com.genus.GENUS_SUP.Entity.Matiere;
import com.genus.GENUS_SUP.Entity.Planification;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.PlanificationMapper;
import com.genus.GENUS_SUP.Repository.AnneeAcademiqueRepository;
import com.genus.GENUS_SUP.Repository.MatiereRepository;
import com.genus.GENUS_SUP.Repository.PlanificationRepository;
import com.genus.GENUS_SUP.Service.Interface.IPlanificationService;
import com.genus.GENUS_SUP.dto.planification_dto.PlanificationRequest;
import com.genus.GENUS_SUP.dto.planification_dto.PlanificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        planification.setStatus("En attente");

        //formatage de l'heure de debut en type localTime;
        LocalTime heure = LocalTime.parse(planificationRequest.getDebut());
        //Addition de l'heure de debut avec la duree pour avoir l'heure de fin;
        LocalTime resultat = heure.plusHours(planificationRequest.getDuree());
        //Enregistrement de la date de fin dans sa variable en reconversion vers le type string;
        planification.setFin(resultat.toString());


        Planification planificationSaved = this.planificationRepo.save(planification);
//        this.emailService.sendUserNotificationEmail(planificationSaved.getMatiere().getEnseignant());
        return this.planificationMapper.fromPlanification(planificationSaved);
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
//        this.emailService.sendUserNotificationEmail(planificationEdited.getMatiere().getEnseignant());
        return this.planificationMapper.fromPlanification(planificationEdited);    }

    @Override
    public PlanificationResponse editStatus(Long id) {
        Planification planification = this.planificationRepo.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("Planification with ID "+id+"not found"));
        planification.setStatus("Complete");
        return this.planificationMapper.fromPlanification(this.planificationRepo.save(planification));
    }

    @Override
    public PlanificationResponse cancelPlanning(Long id) {
            Planification planification = this.planificationRepo.findById(id)
                    .orElseThrow(()-> new RessourceNotFoundException("Planification with ID "+id+"not found"));
            planification.setStatus("Annule");
            return this.planificationMapper.fromPlanification(this.planificationRepo.save(planification));
    }

    @Scheduled(fixedRate = 86400000) // exécute toutes les 24 heures (86400000 ms = 24 heures)
    public void checkAndExpirePendingPlanification() {
        List<Planification> pendingPlanification = this.planificationRepo.findByStatus("En attente");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME; // Format ISO 8601

        for (Planification planification : pendingPlanification) {
            LocalDateTime planificationDateTime = LocalDateTime.parse(planification.getJour(), formatter);
            LocalDate planificationDate = planificationDateTime.toLocalDate().plusDays(1); // Convertir en LocalDate
            // Comparer les dates sans l'heure
            if (planificationDate.isBefore(today)) {
                planification.setStatus("Expire");
                this.planificationRepo.save(planification);
            }
        }
    }
}
