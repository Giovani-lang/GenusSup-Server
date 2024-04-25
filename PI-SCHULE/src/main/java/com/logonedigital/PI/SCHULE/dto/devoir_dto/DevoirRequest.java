package com.logonedigital.PI.SCHULE.dto.devoir_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevoirRequest {
    @NotNull(message = "required field")
    private Long matiereId,anneeAcademiqueId;
    @NotEmpty(message = "required field")
    private String titre, description, lien;
}
