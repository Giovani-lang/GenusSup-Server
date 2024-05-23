package com.logonedigital.PI.SCHULE.dto.appartenance_dto;

import com.logonedigital.PI.SCHULE.Model.AnneeAcademiqueModel;
import com.logonedigital.PI.SCHULE.Model.EtudiantModel;
import com.logonedigital.PI.SCHULE.Model.OptionModel;
import com.logonedigital.PI.SCHULE.dto.option_dto.OptionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppartenanceResponse {
    private Long id;
    private EtudiantModel etudiant;
    private OptionModel option;
    private AnneeAcademiqueModel anneeAcademique;
}
