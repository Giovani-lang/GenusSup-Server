package com.genus.GENUS_SUP.dto.enseignant_dto;

import com.genus.GENUS_SUP.Model.EcoleModel;
import com.genus.GENUS_SUP.Model.MatiereModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String image_url;
    private String nom;
    private String prenom;
    private String password;
    private String telephone;
    private String genre;
    private String role;
    private EcoleModel ecole;
    private String grade;
    private String status;
    private List<MatiereModel> matieres;
    private Date createdAt;
    private Date updatedAt;

}
