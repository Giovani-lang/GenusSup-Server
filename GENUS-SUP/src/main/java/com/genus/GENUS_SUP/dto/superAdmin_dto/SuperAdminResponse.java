package com.genus.GENUS_SUP.dto.superAdmin_dto;

import com.genus.GENUS_SUP.Model.EcoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperAdminResponse {
    private Long id;
    private String username;
    private String email;
    private String nom;
    private String image_url;
    private String prenom;
    private String password;
    private String telephone;
    private String genre;
    private String role;
    private String status;
    private EcoleModel ecole;
}
