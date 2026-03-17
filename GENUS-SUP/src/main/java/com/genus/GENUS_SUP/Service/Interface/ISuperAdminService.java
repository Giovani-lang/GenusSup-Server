package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.superAdmin_dto.SuperAdminRequest;
import com.genus.GENUS_SUP.dto.superAdmin_dto.SuperAdminResponse;

import java.util.List;

public interface ISuperAdminService {

    SuperAdminResponse addSuperAdmin(SuperAdminRequest superAdmin);
    SuperAdminResponse getSuperAdmin(Long id);
    List<SuperAdminResponse> getSAdminByEcole(Long ecoleId);
    SuperAdminResponse editSuperAdmin(String email,SuperAdminRequest superAdmin);
}
