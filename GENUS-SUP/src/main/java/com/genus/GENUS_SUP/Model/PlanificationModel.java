package com.genus.GENUS_SUP.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanificationModel {
    private Long id;
    private String Jour;
    private int duree;
    private MatiereModel matiere;
    private AnneeAcademiqueModel anneeAcademique;
}
