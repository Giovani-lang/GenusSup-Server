package com.genus.GENUS_SUP.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionModel {
    private Long id;
    private String nom;
    private TarifModel tarif;
    private ClasseModel classe;
}
