package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.admin_dto.AdminRequestDTO;
import com.genus.GENUS_SUP.dto.admin_dto.AdminResponseDTO;

import java.util.List;

public interface IAdminService {
    AdminResponseDTO addAdministration(AdminRequestDTO adminRequestDTO);
    List<AdminResponseDTO> getAdministrations();
    List<AdminResponseDTO> getAdministrationsByEcole(Long ecoleId);
    AdminResponseDTO getAdministration(String email);
    AdminResponseDTO updateAdministration(String email,AdminRequestDTO adminRequestDTO);

}
