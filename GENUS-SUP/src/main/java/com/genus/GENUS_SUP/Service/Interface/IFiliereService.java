package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.filiere_dto.FiliereRequest;
import com.genus.GENUS_SUP.dto.filiere_dto.FiliereResponse;

import java.util.List;

public interface IFiliereService {
    FiliereResponse addFiliere(FiliereRequest filiereRequest);
    List<FiliereResponse> getFiliere(Long id);
    FiliereResponse editFiliere (Long id, FiliereRequest filiereRequest);
    FiliereResponse deleteFiliere (Long id);
}
