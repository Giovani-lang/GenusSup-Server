package com.genus.GENUS_PRIMO.dto.anneeAcademique_dto;

import com.genus.GENUS_PRIMO.Model.EcoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnneeAcademiqueResponse {
    private Long id;
    private String annees;
    private EcoleModel ecole;
}
