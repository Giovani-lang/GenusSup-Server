package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.appartenance_dto.AppartenanceRequest;
import com.genus.GENUS_SUP.dto.appartenance_dto.AppartenanceResponse;

import java.util.List;

public interface IAppartenanceService {

    AppartenanceResponse addAppartenance (AppartenanceRequest appartenanceRequest);
    AppartenanceResponse editAppartenance (Long id, AppartenanceRequest appartenanceRequest);
    List<AppartenanceResponse> getAllAppartenances (Long ecoleId);
    List<AppartenanceResponse> getAllAppartenancesByEtudiant (Long etudiantId);
    List<AppartenanceResponse> getAllAppartenancesByOpiton (Long anneeAcademiqueId,Long optionId);
    List<AppartenanceResponse> getAllAppartenancesByAnneeAcademique (Long ecoleId,Long anneeAcademiqueId);
    AppartenanceResponse getAppartenancesByEtudiantAndAnneeAcademique (Long etdId,Long anId);
}
