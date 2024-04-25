package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.Ecole;
import com.logonedigital.PI.SCHULE.dto.ecole_dto.EcoleRequest;
import com.logonedigital.PI.SCHULE.dto.ecole_dto.EcoleResponse;

import java.util.List;

public interface IEcoleService {

    EcoleResponse addEcole (EcoleRequest ecole);
    EcoleResponse getEcole (Long id);
    List<EcoleResponse> getAllEcole();
    EcoleResponse editEcole(Long id, EcoleRequest ecole);
}
