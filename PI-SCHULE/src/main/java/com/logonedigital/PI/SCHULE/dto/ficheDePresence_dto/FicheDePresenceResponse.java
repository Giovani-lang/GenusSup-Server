package com.logonedigital.PI.SCHULE.dto.ficheDePresence_dto;


import com.logonedigital.PI.SCHULE.Model.EtudiantModel;
import com.logonedigital.PI.SCHULE.Model.PlanificationModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheDePresenceResponse {
    private Long id;
    private boolean isAbsent;
    private EtudiantModel etudiant;
    private PlanificationModel planification;
}
