package com.genus.GENUS_SUP.dto.classe_dto;

import com.genus.GENUS_SUP.Model.FiliereModel;
import com.genus.GENUS_SUP.Model.OptionModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseResponse {
    private Long id;
    private String nom;
    private int niveau;
    private FiliereModel filiere;
    private List<OptionModel> options;
}
