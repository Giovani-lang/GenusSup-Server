package com.genus.GENUS_SUP.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseModel {
    private Long id;
    private String nom;
    private int niveau;
    private CycleModel cycle;
}
