package com.logonedigital.PI.SCHULE.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatiereModel {
    private Long id;
    private String module;
    private String intitule;
    private String code;
    private int coefficient;
    private OptionModel option;
    private EnseignantModel enseignant;
}
