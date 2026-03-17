package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.departement_dto.DepartementRequest;
import com.genus.GENUS_SUP.dto.departement_dto.DepartementResponse;

import java.util.List;

public interface IDepartementService {
    DepartementResponse addDepartement(DepartementRequest departementRequest);
    List<DepartementResponse> getDepartement(Long id);
    DepartementResponse editDepartement (Long id, DepartementRequest departementRequest);
    DepartementResponse deleteDepartement (Long id);
}
