package com.logonedigital.PI.SCHULE.dto.appartenance_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppartenanceRequest {

    @NotNull(message = "required field")
    private Long etudiantId;
    @NotNull(message = "required field")
    private Long optionId;
    @NotNull(message = "required field")
    private Long anneeAcademiqueId;
    @NotEmpty(message = "required field")
    private String status;
}
