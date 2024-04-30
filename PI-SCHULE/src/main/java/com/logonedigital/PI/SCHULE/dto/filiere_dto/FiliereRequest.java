package com.logonedigital.PI.SCHULE.dto.filiere_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiliereRequest {
    @NotEmpty(message = "required field")
    private String nom;
    @NotNull(message = "required field")
    private Long cycleId;
    private boolean isDeleted;
}
