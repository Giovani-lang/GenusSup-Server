package com.genus.GENUS_SUP.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifModel {
    private Long id;
    private double montant;
    private List<TrancheModel> tranches;
}
