package com.logonedigital.PI.SCHULE.dto.enseignant_dto;

import com.logonedigital.PI.SCHULE.dto.ficheDePresence_dto.FicheDePresenceRequest;
import com.logonedigital.PI.SCHULE.dto.note_dto.NoteRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnseignantRequestDTO {
    @Email(message = "invalid email")
    @NotEmpty(message = "required field")
    private String email;
    private String image_url;
    @NotEmpty(message = "required field")
    private String nom;
    @NotEmpty(message = "required field")
    private String prenom;
    @NotEmpty(message = "required field")
    private String telephone;
    @NotEmpty(message = "required field")
    private String password;
    @NotEmpty(message = "required field")
    private String genre;
    @NotNull(message = "required field")
    private Long ecoleId;
    @NotEmpty(message = "required field")
    private String grade;
    private String status;
    @NotNull(message = "required field")
    private Long filiereId;
    @NotNull(message = "required field")
    private List<Long> matieresIds;
    @NotNull(message = "required field")
    private boolean chiefDepartment;
}
