package com.genus.GENUS_PRIMO.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanificationModel {
    private Long id;
    private String Jour;
    private MatiereModel matiere;
}
