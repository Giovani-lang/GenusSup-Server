package com.genus.GENUS_SUP.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrancheModel {
    private Long id;
    private Long numero;
    private double montant;
    private String date;
}
