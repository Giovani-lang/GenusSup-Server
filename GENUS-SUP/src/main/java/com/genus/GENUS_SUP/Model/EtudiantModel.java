package com.genus.GENUS_SUP.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantModel {
    private Long id;
    private String image_url;
    private String matricule;
    private String nom;
    private String prenom;
    private EcoleModel ecole;
}
