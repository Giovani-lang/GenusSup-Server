package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.Enseignant;
import com.logonedigital.PI.SCHULE.Entity.FicheDePresence;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.dto.ficheDePresence_dto.FicheDePresenceRequest;
import com.logonedigital.PI.SCHULE.dto.ficheDePresence_dto.FicheDePresenceResponse;
import com.logonedigital.PI.SCHULE.dto.ficheDePresence_dto.FicheDePresenceUpdated;

import java.util.List;

public interface IFicheDePresenceService {

    List<FicheDePresenceResponse> addFicheDePresence(List<FicheDePresenceRequest> ficheDePresence);
    FicheDePresenceResponse addJustification(FicheDePresenceRequest ficheDePresence);
    List<FicheDePresenceResponse> getFichesDePresence(Long ecoleId);
    List<FicheDePresenceResponse> getEtudiantList(String email);
    List<FicheDePresenceResponse> updateFicheDePresence(List<FicheDePresenceUpdated> ficheDePresence);

}
