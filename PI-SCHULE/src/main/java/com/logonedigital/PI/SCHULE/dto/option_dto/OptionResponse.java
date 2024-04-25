package com.logonedigital.PI.SCHULE.dto.option_dto;

import com.logonedigital.PI.SCHULE.Model.ClasseModel;
import com.logonedigital.PI.SCHULE.Model.FiliereModel;
import com.logonedigital.PI.SCHULE.Model.TarifModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponse {
    private Long id;
    private String nom;
    private TarifModel tarif;
    private ClasseModel classe;
}
