package com.logonedigital.PI.SCHULE.dto.classe_dto;

import com.logonedigital.PI.SCHULE.Model.FiliereModel;
import com.logonedigital.PI.SCHULE.Model.OptionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseResponse {
    private Long id;
    private String nom;
    private String niveau;
    private FiliereModel filiere;
    private List<OptionModel> options;
}
