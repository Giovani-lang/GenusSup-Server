package com.genus.GENUS_SUP.dto.superAdmin_dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class SuperAdminRequest {
    @Email(message = "invalid email")
    @NotEmpty(message = "invalid email")
    private String email;
    private String image_url;
    @NotEmpty(message =  "required field")
    private String nom;
    @NotEmpty(message =  "required field")
    private String prenom;
    @NotEmpty(message =  "required field")
    private String password;
    @NotEmpty(message =  "required field")
    private String telephone;
    @NotEmpty(message =  "required field")
    private String genre;
    private String status;
    @NotEmpty(message =  "required field")
    private String poste;
    @NotNull(message = "required field")
    private Long ecoleId;
}
