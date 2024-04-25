package com.logonedigital.PI.SCHULE.Model;

import com.logonedigital.PI.SCHULE.dto.matiere_dto.MatiereResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanificationModel {
    private Long id;
    private String Jour;
    private MatiereModel matiere;
}
