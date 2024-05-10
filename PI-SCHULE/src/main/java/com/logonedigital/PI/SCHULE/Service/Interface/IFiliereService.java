package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.Filiere;
import com.logonedigital.PI.SCHULE.dto.filiere_dto.FiliereRequest;
import com.logonedigital.PI.SCHULE.dto.filiere_dto.FiliereResponse;

import java.util.List;

public interface IFiliereService {
    FiliereResponse addFiliere (FiliereRequest filiereRequest);
    List<FiliereResponse> getFiliere (Long ecoleId);
    List<FiliereResponse> getFiliereByCycle (Long cycleId);
    FiliereResponse updateFiliere (Long id,FiliereRequest filiereRequest);
    FiliereResponse deleteFiliere (Long id);

}
