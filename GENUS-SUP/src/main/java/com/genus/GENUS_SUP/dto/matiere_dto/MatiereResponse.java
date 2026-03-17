package com.genus.GENUS_SUP.dto.matiere_dto;

import com.genus.GENUS_SUP.dto.option_dto.OptionResponse;
import com.genus.GENUS_SUP.Model.EnseignantModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatiereResponse {
    private Long id;
    private String module;
    private String code;
    private String intitule;
    private int coefficient;
    private Double montant;
    private OptionResponse option;
    private EnseignantModel enseignant;
}
