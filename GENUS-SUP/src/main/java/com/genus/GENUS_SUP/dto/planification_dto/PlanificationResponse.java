package com.genus.GENUS_SUP.dto.planification_dto;

import com.genus.GENUS_SUP.dto.matiere_dto.MatiereResponse;
import com.genus.GENUS_SUP.Model.AnneeAcademiqueModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanificationResponse {

    private Long id;
    private String Jour;
    private String debut;
    private String fin;
    private String salle;
    private String status;
    private int duree;
    private MatiereResponse matiere;
    private AnneeAcademiqueModel anneeAcademique;
}
