package com.logonedigital.PI.SCHULE.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifModel {
    private Long id;
    private double montant;
}
