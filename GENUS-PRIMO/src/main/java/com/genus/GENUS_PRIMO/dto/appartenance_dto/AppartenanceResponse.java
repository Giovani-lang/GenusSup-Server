package com.genus.GENUS_PRIMO.dto.appartenance_dto;

import com.genus.GENUS_PRIMO.Model.AnneeAcademiqueModel;
import com.genus.GENUS_PRIMO.Model.EtudiantModel;
import com.genus.GENUS_PRIMO.Model.OptionModel;
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
