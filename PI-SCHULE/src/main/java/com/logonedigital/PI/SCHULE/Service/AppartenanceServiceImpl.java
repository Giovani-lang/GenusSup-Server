package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.AnneeAcademique;
import com.logonedigital.PI.SCHULE.Entity.Appartenance;
import com.logonedigital.PI.SCHULE.Entity.Etudiant;
import com.logonedigital.PI.SCHULE.Entity.Option;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.AppartenanceMapper;
import com.logonedigital.PI.SCHULE.Repository.AnneeAcademiqueRepository;
import com.logonedigital.PI.SCHULE.Repository.AppartenanceRepository;
import com.logonedigital.PI.SCHULE.Repository.EtudiantRepository;
import com.logonedigital.PI.SCHULE.Repository.OptionRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IAppartenanceService;
import com.logonedigital.PI.SCHULE.dto.appartenance_dto.AppartenanceRequest;
import com.logonedigital.PI.SCHULE.dto.appartenance_dto.AppartenanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AppartenanceServiceImpl implements IAppartenanceService {
    private final AppartenanceRepository appartenanceRepo;
    private final AnneeAcademiqueRepository anneeAcademiqueRepo;
    private final EtudiantRepository etudiantRepo;
    private final OptionRepository optionRepo;
    private final AppartenanceMapper appartenanceMapper;

    @Override
    public AppartenanceResponse addAppartenance(AppartenanceRequest appartenanceRequest) {
        Appartenance appartenance = this.appartenanceMapper.fromAppartenanceRequest(appartenanceRequest);
        Etudiant etudiant = this.etudiantRepo.findById(appartenanceRequest.getEtudiantId())
                .orElseThrow(()-> new RessourceNotFoundException("Not etudiant matches with id: "+appartenanceRequest.getEtudiantId()));
        appartenance.setEtudiant(etudiant);
        Option option = this.optionRepo.findById(appartenanceRequest.getOptionId())
                .orElseThrow(()-> new RessourceNotFoundException("Not Option matches with id: "+appartenanceRequest.getOptionId()));
        appartenance.setOption(option);
        AnneeAcademique anneeAcademique = this.anneeAcademiqueRepo.findById(appartenanceRequest.getAnneeAcademiqueId())
                .orElseThrow(()-> new RessourceNotFoundException("Not annee academique matches with id: "+appartenanceRequest.getAnneeAcademiqueId()));
        appartenance.setAnneeAcademique(anneeAcademique);
        return this.appartenanceMapper.fromAppartenance(this.appartenanceRepo.save(appartenance));
    }

    @Override
    public AppartenanceResponse editAppartenance(Long id, AppartenanceRequest appartenanceRequest) {
       try {
           Appartenance appartenanceUpdated = this.appartenanceRepo.findById(id).get();
           Etudiant etudiant = this.etudiantRepo.findById(appartenanceRequest.getEtudiantId())
                   .orElseThrow(()-> new RessourceNotFoundException("Not etudiant matches with id: "+appartenanceRequest.getEtudiantId()));
           appartenanceUpdated.setEtudiant(etudiant);
           Option option = this.optionRepo.findById(appartenanceRequest.getOptionId())
                   .orElseThrow(()-> new RessourceNotFoundException("Not Option matches with id: "+appartenanceRequest.getOptionId()));
           appartenanceUpdated.setOption(option);
           AnneeAcademique anneeAcademique = this.anneeAcademiqueRepo.findById(appartenanceRequest.getAnneeAcademiqueId())
                   .orElseThrow(()-> new RessourceNotFoundException("Not annee academique matches with id: "+appartenanceRequest.getAnneeAcademiqueId()));
           appartenanceUpdated.setAnneeAcademique(anneeAcademique);
           return this.appartenanceMapper.fromAppartenance(this.appartenanceRepo.saveAndFlush(appartenanceUpdated));
       }catch (NoSuchElementException e){
           throw new RessourceNotFoundException("Not found");
       }
    }

    @Override
    public List<AppartenanceResponse> getAllAppartenances(Long ecoleId) {
        List<Appartenance> appartenances = this.appartenanceRepo.findByEcole(ecoleId);
        List<AppartenanceResponse> appartenanceResponses = new ArrayList<>();
        appartenances.forEach(appartenance -> appartenanceResponses.add(this.appartenanceMapper.fromAppartenance(appartenance)));
        return appartenanceResponses;
    }

    @Override
    public List<AppartenanceResponse> getAllAppartenancesByEtudiant(Long etudiantId) {
        List<Appartenance> appartenances = this.appartenanceRepo.findByEtudiantId(etudiantId);
        List<AppartenanceResponse> appartenanceResponses = new ArrayList<>();
        appartenances.forEach(appartenance -> appartenanceResponses.add(this.appartenanceMapper.fromAppartenance(appartenance)));
        return appartenanceResponses;
    }

    @Override
    public List<AppartenanceResponse> getAllAppartenancesByOpiton(Long anneeAcademiqueId,Long optionId) {
        List<Appartenance> appartenances = this.appartenanceRepo.findByOptionId(anneeAcademiqueId,optionId);
        List<AppartenanceResponse> appartenanceResponses = new ArrayList<>();
        appartenances.forEach(appartenance -> appartenanceResponses.add(this.appartenanceMapper.fromAppartenance(appartenance)));
        return appartenanceResponses;
    }

    @Override
    public List<AppartenanceResponse> getAllAppartenancesByAnneeAcademique(Long ecoleId,Long AnneeAcademiqueId) {
        List<Appartenance> appartenances = this.appartenanceRepo.findByAnneeId(ecoleId,AnneeAcademiqueId);
        List<AppartenanceResponse> appartenanceResponses = new ArrayList<>();
        appartenances.forEach(appartenance -> appartenanceResponses.add(this.appartenanceMapper.fromAppartenance(appartenance)));
        return appartenanceResponses;
    }

    @Override
    public AppartenanceResponse getAppartenancesByEtudiantAndAnneeAcademique(Long EtdId, Long AnId) {
        return this.appartenanceMapper.fromAppartenance(this.appartenanceRepo.findByEtudiantIdAndAnneeId(EtdId, AnId));
    }
}
