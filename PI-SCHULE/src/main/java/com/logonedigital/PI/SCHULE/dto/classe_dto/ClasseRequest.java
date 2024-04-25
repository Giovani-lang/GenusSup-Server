package com.logonedigital.PI.SCHULE.dto.classe_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseRequest {
    @NotEmpty(message = "required field")
    private String nom;
    @NotEmpty(message = "required field")
    private String niveau;
    @NotNull(message = "required field")
    private Long filiereId;
}
