package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.*;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.BulletinMapper;
import com.genus.GENUS_SUP.Mapper.SalaireConfigMapper;
import com.genus.GENUS_SUP.Repository.*;
import com.genus.GENUS_SUP.dto.bulletin_dto.BulletinRequest;
import com.genus.GENUS_SUP.dto.salaireConfig_dto.SalaireConfigRequest;
import com.genus.GENUS_SUP.dto.salaireConfig_dto.SalaireConfigResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SalaireConfigService {

    private final SalaireConfigRepository salaireConfigRepo;
    private final EnseignantRepository enseignantRepo;
    private final EmargementRepository emargementRepo;
    private final BulletinRepository bulletinRepo;
    private final BulletinMapper bulletinMapper;
    private final EcoleRepository ecoleRepo;
    private final SalaireConfigMapper salaireConfigMapper;

    public SalaireConfigResponse addPayDay (SalaireConfigRequest salaireConfigRequest){
        SalaireConfig config = this.salaireConfigMapper.fromSalaireConfigRequest(salaireConfigRequest);
        Ecole ecole = this.ecoleRepo.findById(salaireConfigRequest.getEcoleId())
                .orElseThrow(()-> new RessourceNotFoundException("Not found"));
        config.setEcole(ecole);
       return this.salaireConfigMapper.fromSalaireConfig(this.salaireConfigRepo.save(config));
    }
    public SalaireConfigResponse updatePayDay (Long id,SalaireConfigRequest salaireConfigRequest){
        SalaireConfig config = this.salaireConfigMapper.fromSalaireConfigRequest(salaireConfigRequest);
        SalaireConfig configUpdated = this.salaireConfigRepo.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("No config matches to this ID :" + id));
        configUpdated.setJour(config.getJour());
       return this.salaireConfigMapper.fromSalaireConfig(this.salaireConfigRepo.save(configUpdated));
    }

    public SalaireConfigResponse getPayDay(Long ecoleId){
        return this.salaireConfigMapper.fromSalaireConfig(this.salaireConfigRepo.findByEcoleId(ecoleId));
    }

    public int getJourDePaieForEcole(Long ecoleId) {
        SalaireConfig config = salaireConfigRepo.findByEcoleId(ecoleId);
        return config != null ? config.getJour() : -1;
    }
    @Scheduled(cron = "0 30 7 * * ?") // Exécution tous les jours à 7h30
    public void calculerSalaire() {
        List<Ecole> ecoles = ecoleRepo.findAll();
        LocalDate today = LocalDate.now();

        for (Ecole ecole : ecoles) {
            int jourDePaie = this.getJourDePaieForEcole(ecole.getId());

            if (jourDePaie == today.getDayOfMonth()) {
                // Appeler la méthode de calcul du salaire
                calculateSalaries(today.getDayOfMonth());
            }
        }
    }

    public void calculateSalaries(int jour) {
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(jour).minusMonths(1);
        LocalDate endDate = now.withDayOfMonth(jour);

        // Récupérer tous les enseignants
        List<Enseignant> enseignants = enseignantRepo.findAll();
        for (Enseignant enseignant : enseignants) {
            // Vérifier si le jour de paie est configuré pour l'école de cet enseignant
            int jourDePaie = getJourDePaieForEcole(enseignant.getEcole().getId());
            if (jourDePaie == -1) {
                System.out.println("Le jour de paie n'est pas configuré pour l'école ID: " + enseignant.getEcole().getId());
                continue; // Passer à l'enseignant suivant si la configuration n'existe pas
            }
            double totalSalary = 0.0;
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            String startDateStr = startDate.format(formatter);
            String endDateStr = endDate.format(formatter);
            List<Emargement> emargements = emargementRepo.findByPlanificationDateBetween(enseignant.getId(), startDateStr, endDateStr);
            AnneeAcademique annee = null;
            for (Emargement emargement : emargements) {
                annee = emargement.getPlanification().getAnneeAcademique();
                // Calculer le salaire en fonction de la présence et du prix de la matière
                double prixMatiere = emargement.getPlanification().getMatiere().getMontant() ;
                double heuresEmargees = emargement.getPlanification().getDuree();
                totalSalary += prixMatiere * heuresEmargees;
            }

            if(totalSalary != 0.0){
            BulletinRequest bulletin = new BulletinRequest();
            Bulletin bulletinSaved = this.bulletinMapper.fromBulletinRequest(bulletin);
            bulletinSaved.setEnseignant(enseignant);
            bulletinSaved.setDate(new Date());
            bulletinSaved.setMontant(totalSalary);
            bulletinSaved.setAnneeAcademique(annee);
            this.bulletinMapper.fromBulletin(this.bulletinRepo.save(bulletinSaved));
            }
        }
    }
}
