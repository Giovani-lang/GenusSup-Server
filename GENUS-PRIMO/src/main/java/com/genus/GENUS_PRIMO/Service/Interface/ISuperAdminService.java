package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.superAdmin_dto.SuperAdminRequest;
import com.genus.GENUS_PRIMO.dto.superAdmin_dto.SuperAdminResponse;

import java.util.List;

public interface ISuperAdminService {

    SuperAdminResponse addSuperAdmin(SuperAdminRequest superAdmin);
    SuperAdminResponse getSuperAdmin(Long id);
    List<SuperAdminResponse> getAllSuperAdmin();
    SuperAdminResponse editSuperAdmin(String email,SuperAdminRequest superAdmin);
}
