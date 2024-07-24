package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.ecole_dto.EcoleRequest;
import com.genus.GENUS_PRIMO.dto.ecole_dto.EcoleResponse;

import java.util.List;

public interface IEcoleService {

    EcoleResponse addEcole (EcoleRequest ecole);
    EcoleResponse getEcole (Long id);
    List<EcoleResponse> getAllEcole();
    EcoleResponse editEcole(Long id, EcoleRequest ecole);
}
