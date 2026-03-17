package com.genus.GENUS_SUP.dto.user_dto;


import com.genus.GENUS_SUP.Model.EcoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String username;
    private String email;
    private String image_url;
    private String nom;
    private String prenom;
    private String password;
    private String telephone;
    private String genre;
    private String role;
    private String status;
    private EcoleModel ecole;
    private boolean firstLogin;
}
