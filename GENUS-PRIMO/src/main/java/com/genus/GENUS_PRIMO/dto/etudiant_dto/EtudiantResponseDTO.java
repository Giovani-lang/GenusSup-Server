package com.genus.GENUS_PRIMO.dto.etudiant_dto;


import com.genus.GENUS_PRIMO.Model.EcoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantResponseDTO {
    private Long id;
    private String username;
    private String matricule;
    private String image_url;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String password;
    private String role;
    private String genre;
    private String status;
    private EcoleModel ecole;
    private List<String> dossiers;
}
