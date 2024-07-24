package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.paiement_dto.PaiementRequest;
import com.genus.GENUS_PRIMO.dto.paiement_dto.PaiementResponse;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;

import java.util.List;

public interface IPaiementService {
    PaiementResponse addPaiement (PaiementRequest paiementRequest);
    List<PaiementResponse> getPaiement(Long etudiantId, Long anneeId) throws RessourceNotFoundException;

    PaiementResponse getById(Long id);
}
