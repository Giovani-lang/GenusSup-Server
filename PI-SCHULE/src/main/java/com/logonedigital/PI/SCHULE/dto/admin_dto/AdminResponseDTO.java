package com.logonedigital.PI.SCHULE.dto.admin_dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.logonedigital.PI.SCHULE.Model.EcoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDTO {
    private String email;
    private String nom;
    private String image_url;
    private String prenom;
    private String password;
    private String telephone;
    private String genre;
    private String role;
    private String poste;
    private String status;
    private EcoleModel ecole;
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

}
