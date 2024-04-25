package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.SuperAdmin;
import com.logonedigital.PI.SCHULE.dto.superAdmin_dto.SuperAdminRequest;
import com.logonedigital.PI.SCHULE.dto.superAdmin_dto.SuperAdminResponse;

import java.util.List;

public interface ISuperAdminService {

    SuperAdminResponse addSuperAdmin(SuperAdminRequest superAdmin);
    SuperAdminResponse getSuperAdmin(Long id);
    List<SuperAdminResponse> getAllSuperAdmin();
    SuperAdminResponse editSuperAdmin(String email,SuperAdminRequest superAdmin);
}
