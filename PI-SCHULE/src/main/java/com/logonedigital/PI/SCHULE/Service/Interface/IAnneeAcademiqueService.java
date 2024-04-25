package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.AnneeAcademique;
import com.logonedigital.PI.SCHULE.dto.anneeAcademique_dto.AnneeAcademiqueRequest;
import com.logonedigital.PI.SCHULE.dto.anneeAcademique_dto.AnneeAcademiqueResponse;

import java.util.List;

public interface IAnneeAcademiqueService {

    AnneeAcademiqueResponse addAnnee (AnneeAcademiqueRequest anneeAcademiqueRequest);
    AnneeAcademiqueResponse getAnnee(Long id);
    List<AnneeAcademiqueResponse> getAllAnnee(Long ecoleId);
    AnneeAcademiqueResponse editAnnee(Long id, AnneeAcademiqueRequest anneeAcademiqueRequest);
}
