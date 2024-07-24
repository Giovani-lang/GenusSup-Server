package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.classe_dto.ClasseRequest;
import com.genus.GENUS_PRIMO.dto.classe_dto.ClasseResponse;

import java.util.List;

public interface IClasseService {
    ClasseResponse addClasse (ClasseRequest classeRequest);
    List<ClasseResponse> getClasse (Long ecoleId);
    List<ClasseResponse> getClasseByFiliere (Long filiereId);
    ClasseResponse updateClasse (Long id,ClasseRequest classeRequest);
    ClasseResponse deleteClasse (Long id);
}
