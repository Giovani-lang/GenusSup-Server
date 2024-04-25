package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.dto.paiement_dto.PaiementRequest;
import com.logonedigital.PI.SCHULE.dto.paiement_dto.PaiementResponse;

import java.util.List;

public interface IPaiementService {
    PaiementResponse addPaiement (PaiementRequest paiementRequest);
    List<PaiementResponse> getPaiement(Long etudiantId, Long anneeId) throws RessourceNotFoundException;

    PaiementResponse getById(Long id);
}
