package com.genus.GENUS_SUP.dto.filiere_dto;

import com.genus.GENUS_SUP.Entity.Classe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiliereResponse {
    private Long  id;
    private String nom;
    private List<Classe> classes;
}
