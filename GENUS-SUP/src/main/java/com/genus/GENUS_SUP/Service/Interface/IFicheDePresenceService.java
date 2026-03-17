package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.ficheDePresence_dto.FicheDePresenceRequest;
import com.genus.GENUS_SUP.dto.ficheDePresence_dto.FicheDePresenceResponse;

import java.util.List;

public interface IFicheDePresenceService {

    List<FicheDePresenceResponse> addFicheDePresence(List<FicheDePresenceRequest> ficheDePresence);
    FicheDePresenceResponse addJustification(FicheDePresenceRequest ficheDePresence);
    List<FicheDePresenceResponse> getFichesDePresence(Long ecoleId);
    List<FicheDePresenceResponse> getEtudiantList(String matricule);
}
