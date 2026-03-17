package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.enseignant_dto.EnseignantRequestDTO;
import com.genus.GENUS_SUP.dto.enseignant_dto.EnseignantResponseDTO;


import java.util.List;

public interface IEnseignantService {
    EnseignantResponseDTO addEnseignant(EnseignantRequestDTO enseignantRequestDTO);
    EnseignantResponseDTO getEnseignant(String email);
    List<EnseignantResponseDTO> getEnseignants();
    List<EnseignantResponseDTO> getEnseignantsByEcole(Long ecoleId);
    EnseignantResponseDTO updateEnseignant(String email,EnseignantRequestDTO enseignantRequestDTO);
}
