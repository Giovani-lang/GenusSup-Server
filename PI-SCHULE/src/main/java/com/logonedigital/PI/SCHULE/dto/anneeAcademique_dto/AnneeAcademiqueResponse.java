package com.logonedigital.PI.SCHULE.dto.anneeAcademique_dto;

import com.logonedigital.PI.SCHULE.Model.EcoleModel;
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
