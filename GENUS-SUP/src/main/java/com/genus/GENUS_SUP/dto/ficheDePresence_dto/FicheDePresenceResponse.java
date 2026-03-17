package com.genus.GENUS_SUP.dto.ficheDePresence_dto;


import com.genus.GENUS_SUP.Model.EtudiantModel;
import com.genus.GENUS_SUP.Model.PlanificationModel;
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
