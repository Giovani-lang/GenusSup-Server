package com.logonedigital.PI.SCHULE.dto.filiere_dto;

import com.logonedigital.PI.SCHULE.Model.ClasseModel;
import com.logonedigital.PI.SCHULE.Model.CycleModel;
import com.logonedigital.PI.SCHULE.Model.EcoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiliereResponse {
    private CycleModel cycle;
    private Long id;
    private String nom;
    private List<ClasseModel> classes;
}
