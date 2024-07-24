package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.anneeAcademique_dto.AnneeAcademiqueRequest;
import com.genus.GENUS_PRIMO.dto.anneeAcademique_dto.AnneeAcademiqueResponse;

import java.util.List;

public interface IAnneeAcademiqueService {

    AnneeAcademiqueResponse addAnnee (AnneeAcademiqueRequest anneeAcademiqueRequest);
    AnneeAcademiqueResponse getAnnee(Long id);
    List<AnneeAcademiqueResponse> getAllAnnee(Long ecoleId);
    AnneeAcademiqueResponse editAnnee(Long id, AnneeAcademiqueRequest anneeAcademiqueRequest);
}
