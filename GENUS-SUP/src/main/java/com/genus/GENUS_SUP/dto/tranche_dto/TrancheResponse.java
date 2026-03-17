package com.genus.GENUS_SUP.dto.tranche_dto;

import com.genus.GENUS_SUP.Model.TarifModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrancheResponse {
    private Long id;
    private Long numero;
    private double montant;
    private String date;
    private TarifModel tarif;
}
