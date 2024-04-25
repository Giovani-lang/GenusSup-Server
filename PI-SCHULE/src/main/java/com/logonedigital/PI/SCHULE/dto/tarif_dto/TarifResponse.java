package com.logonedigital.PI.SCHULE.dto.tarif_dto;

import com.logonedigital.PI.SCHULE.Model.OptionModel;
import com.logonedigital.PI.SCHULE.dto.option_dto.OptionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifResponse {
    private Long id;
    private double montant;
    private OptionResponse option;
}
