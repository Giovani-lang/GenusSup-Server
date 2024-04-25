package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationRequest;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationResponse;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationTreated;

import java.util.List;

public interface IReclamationService {
    ReclamationResponse addReclamation (ReclamationRequest reclamationRequest);
    ReclamationResponse treatReclamation(ReclamationTreated reclamationTreated);
    ReclamationResponse getReclamation(Long id);
    List<ReclamationResponse> findByEcole(Long ecoleId);
    List<ReclamationResponse> findByEnseignant(Long ensId);
    List<ReclamationResponse> findByEtudiant(Long etdId);
}
