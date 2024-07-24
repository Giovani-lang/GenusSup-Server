package com.genus.GENUS_PRIMO.dto.matiere_dto;

import com.genus.GENUS_PRIMO.dto.option_dto.OptionResponse;
import com.genus.GENUS_PRIMO.Model.EnseignantModel;
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
    private OptionResponse option;
    private EnseignantModel enseignant;
}
