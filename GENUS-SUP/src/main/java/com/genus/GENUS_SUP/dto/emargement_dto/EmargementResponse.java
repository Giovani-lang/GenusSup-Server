package com.genus.GENUS_SUP.dto.emargement_dto;


import com.genus.GENUS_SUP.Model.EnseignantModel;
import com.genus.GENUS_SUP.Model.PlanificationModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmargementResponse {
    private Long id;
    private boolean isPresent;
    private EnseignantModel enseignant;
    private PlanificationModel planification;
}
