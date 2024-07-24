package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.reclamation_dto.ReclamationRequest;
import com.genus.GENUS_PRIMO.dto.reclamation_dto.ReclamationResponse;
import com.genus.GENUS_PRIMO.dto.reclamation_dto.ReclamationTreated;

import java.util.List;

public interface IReclamationService {
    ReclamationResponse addReclamation (ReclamationRequest reclamationRequest);
    ReclamationResponse treatReclamation(ReclamationTreated reclamationTreated);
    ReclamationResponse getReclamation(Long id);
    List<ReclamationResponse> findByEcole(Long ecoleId);
    List<ReclamationResponse> findByEnseignant(Long ensId);
    List<ReclamationResponse> findByEtudiant(Long etdId);
}
