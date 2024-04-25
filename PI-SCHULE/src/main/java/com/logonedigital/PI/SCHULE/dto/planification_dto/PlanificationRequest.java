package com.logonedigital.PI.SCHULE.dto.planification_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanificationRequest {

    @NotEmpty(message = "required field")
    private String Jour;
    @NotEmpty(message = "required field")
    private String debut;
    @NotNull(message = "required field")
    private int duree;
    @NotNull(message = "required field")
    private Long matiereId;
    @NotNull(message = "required field")
    private String salle;
    @NotNull(message = "required field")
    private Long anneeAcademiqueId;
}
