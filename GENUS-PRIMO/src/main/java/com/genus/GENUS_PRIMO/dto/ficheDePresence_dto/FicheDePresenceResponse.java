package com.genus.GENUS_PRIMO.dto.ficheDePresence_dto;


import com.genus.GENUS_PRIMO.Model.EtudiantModel;
import com.genus.GENUS_PRIMO.Model.PlanificationModel;
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
