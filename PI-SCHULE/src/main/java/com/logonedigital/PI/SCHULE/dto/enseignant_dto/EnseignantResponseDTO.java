package com.logonedigital.PI.SCHULE.dto.enseignant_dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.logonedigital.PI.SCHULE.Model.*;
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
    private FiliereModel department;
    private boolean chiefDepartment;
    private Date createdAt;
    private Date updatedAt;

}
