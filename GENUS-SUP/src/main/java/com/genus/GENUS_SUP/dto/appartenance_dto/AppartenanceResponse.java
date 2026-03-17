package com.genus.GENUS_SUP.dto.appartenance_dto;

import com.genus.GENUS_SUP.Model.AnneeAcademiqueModel;
import com.genus.GENUS_SUP.Model.EtudiantModel;
import com.genus.GENUS_SUP.Model.OptionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppartenanceResponse {
    private Long id;
    private EtudiantModel etudiant;
    private OptionModel option;
    private AnneeAcademiqueModel anneeAcademique;
    private String status;
}
