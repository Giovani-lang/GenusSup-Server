package com.genus.GENUS_SUP.dto.option_dto;

import com.genus.GENUS_SUP.Model.ClasseModel;
import com.genus.GENUS_SUP.Model.TarifModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionResponse {
    private Long id;
    private String nom;
    private TarifModel tarif;
    private ClasseModel classe;
}
