package com.genus.GENUS_PRIMO.dto.parent_dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentRequest {
    @Email(message = "invalid email")
    @NotEmpty(message = "invalid email")
    private String email;
    @NotEmpty(message =  "required field")
    private String nom;
    @NotEmpty(message =  "required field")
    private String prenom;
    @NotEmpty(message =  "required field")
    private String telephone;
    @NotEmpty(message =  "required field")
    private String adresse;
    @NotEmpty(message =  "required field")
    private String genre;
    @NotEmpty(message =  "required field")
    private String profession;
    @NotNull(message = "required field")
    private Long etudiantId;
}
