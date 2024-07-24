package com.genus.GENUS_PRIMO.dto.classe_dto;

import com.genus.GENUS_PRIMO.Model.CycleModel;
import com.genus.GENUS_PRIMO.Model.OptionModel;
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
    private CycleModel cycle;
    private List<OptionModel> options;
}
