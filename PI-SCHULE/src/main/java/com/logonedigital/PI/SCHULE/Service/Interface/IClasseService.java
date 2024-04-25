package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.dto.classe_dto.ClasseRequest;
import com.logonedigital.PI.SCHULE.dto.classe_dto.ClasseResponse;

import java.util.List;

public interface IClasseService {
    ClasseResponse addClasse (ClasseRequest classeRequest);
    List<ClasseResponse> getClasse (Long ecoleId);
    List<ClasseResponse> getClasseByFiliere (Long filiereId);
    ClasseResponse updateClasse (String nom,ClasseRequest classeRequest);
}
