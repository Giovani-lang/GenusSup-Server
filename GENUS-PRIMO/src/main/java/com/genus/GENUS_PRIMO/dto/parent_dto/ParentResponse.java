package com.genus.GENUS_PRIMO.dto.parent_dto;

import com.genus.GENUS_PRIMO.Model.EtudiantModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentResponse {
    private Long id;
    private String username;
    private String email;
    private String nom;
    private String prenom;
    private String password;
    private String telephone;
    private String adresse;
    private String genre;
    private String role;
    private String profession;
    private EtudiantModel etudiant;
}
