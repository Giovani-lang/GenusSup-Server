package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.Administration;
import com.logonedigital.PI.SCHULE.dto.admin_dto.AdminRequestDTO;
import com.logonedigital.PI.SCHULE.dto.admin_dto.AdminResponseDTO;
import com.logonedigital.PI.SCHULE.dto.enseignant_dto.EnseignantResponseDTO;

import java.util.List;

public interface AdminService {
    AdminResponseDTO addAdministration(AdminRequestDTO adminRequestDTO);
    List<AdminResponseDTO> getAdministrations();
    List<AdminResponseDTO> getAdministrationsByEcole(Long ecoleId);
    AdminResponseDTO getAdministration(String email);
    AdminResponseDTO updateAdministration(String email,AdminRequestDTO adminRequestDTO);

}
