package com.logonedigital.PI.SCHULE.dto.matiere_dto;

import com.logonedigital.PI.SCHULE.Model.EnseignantModel;
import com.logonedigital.PI.SCHULE.dto.option_dto.OptionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatiereResponse {
    private Long id;
    private String module;
    private String code;
    private String intitule;
    private int coefficient;
    private OptionResponse option;
    private EnseignantModel enseignant;
}
