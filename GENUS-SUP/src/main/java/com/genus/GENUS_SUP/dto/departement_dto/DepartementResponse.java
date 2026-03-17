package com.genus.GENUS_SUP.dto.departement_dto;

import com.genus.GENUS_SUP.Model.CycleModel;
import com.genus.GENUS_SUP.Model.EnseignantModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartementResponse {
    private Long  id;
    private String nom;
    private EnseignantModel chief;
    private List<CycleModel> cycles;
}
