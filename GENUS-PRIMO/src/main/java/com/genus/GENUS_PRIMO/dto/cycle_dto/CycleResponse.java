package com.genus.GENUS_PRIMO.dto.cycle_dto;

import com.genus.GENUS_PRIMO.Model.ClasseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CycleResponse {
    private Long  id;
    private String nom;
    private List<ClasseModel> classes;
}
