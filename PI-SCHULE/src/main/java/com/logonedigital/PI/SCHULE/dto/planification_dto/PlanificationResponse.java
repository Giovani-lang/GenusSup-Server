package com.logonedigital.PI.SCHULE.dto.planification_dto;

import com.logonedigital.PI.SCHULE.Model.AnneeAcademiqueModel;
import com.logonedigital.PI.SCHULE.Model.MatiereModel;
import com.logonedigital.PI.SCHULE.dto.matiere_dto.MatiereResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificationResponse {

    private Long id;
    private String Jour;
    private String debut;
    private String fin;
    private String salle;
    private int duree;
    private MatiereResponse matiere;
    private AnneeAcademiqueModel anneeAcademique;
}
