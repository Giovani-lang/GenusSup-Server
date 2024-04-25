package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.Enseignant;
import com.logonedigital.PI.SCHULE.dto.enseignant_dto.EnseignantRequestDTO;
import com.logonedigital.PI.SCHULE.dto.enseignant_dto.EnseignantResponseDTO;
import com.logonedigital.PI.SCHULE.dto.etudiant_dto.EtudiantResponseDTO;


import java.util.List;

public interface IEnseignantService {
    EnseignantResponseDTO addEnseignant(EnseignantRequestDTO enseignantRequestDTO);
    EnseignantResponseDTO getEnseignant(String email);
    List<EnseignantResponseDTO> getEnseignants();
    List<EnseignantResponseDTO> getEnseignantsByEcole(Long ecoleId);
    EnseignantResponseDTO updateEnseignant(String email,EnseignantRequestDTO enseignantRequestDTO);
}
