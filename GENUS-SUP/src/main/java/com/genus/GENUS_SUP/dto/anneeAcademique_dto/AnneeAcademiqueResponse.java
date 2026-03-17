package com.genus.GENUS_SUP.dto.anneeAcademique_dto;

import com.genus.GENUS_SUP.Model.EcoleModel;
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
