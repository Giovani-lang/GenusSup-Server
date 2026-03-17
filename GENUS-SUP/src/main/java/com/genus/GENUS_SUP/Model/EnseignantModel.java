package com.genus.GENUS_SUP.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantModel {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String grade;
    private EcoleModel ecole;
}
