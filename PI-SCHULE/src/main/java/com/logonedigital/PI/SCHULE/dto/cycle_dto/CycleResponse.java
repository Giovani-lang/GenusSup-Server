package com.logonedigital.PI.SCHULE.dto.cycle_dto;

import com.logonedigital.PI.SCHULE.Model.EcoleModel;
import com.logonedigital.PI.SCHULE.Model.FiliereModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CycleResponse {
    private Long  id;
    private String nom;
    private List<FiliereModel> filieres;
}
