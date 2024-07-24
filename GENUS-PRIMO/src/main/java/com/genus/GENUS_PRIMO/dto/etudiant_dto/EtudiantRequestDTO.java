package com.genus.GENUS_PRIMO.dto.etudiant_dto;



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
public class EtudiantRequestDTO {
    private String image_url;
    @NotEmpty(message = "required field")
    private String nom;
    @NotEmpty(message = "required field")
    private String prenom;
    @NotEmpty(message = "required field")
    private String dateNaissance;
    @NotEmpty(message = "required field")
    private String password;
    @NotEmpty(message = "required field")
    private String genre;
    private String status;
    @NotNull(message = "required field")
    private Long ecoleId;
    private List<String> dossiers;


}
