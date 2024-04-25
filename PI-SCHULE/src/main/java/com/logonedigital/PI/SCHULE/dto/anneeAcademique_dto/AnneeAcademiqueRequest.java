package com.logonedigital.PI.SCHULE.dto.anneeAcademique_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnneeAcademiqueRequest {
    @NotEmpty(message = "required field")
    private String annees;
    @NotNull(message = "required field")
    private Long ecoleId;
}
