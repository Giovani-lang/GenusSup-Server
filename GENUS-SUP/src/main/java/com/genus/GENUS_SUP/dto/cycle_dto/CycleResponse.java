package com.genus.GENUS_SUP.dto.cycle_dto;

import com.genus.GENUS_SUP.Model.ClasseModel;
import com.genus.GENUS_SUP.Model.FiliereModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CycleResponse {
    private Long  id;
    private String nom,section;
    private List<FiliereModel> filieres;
}
