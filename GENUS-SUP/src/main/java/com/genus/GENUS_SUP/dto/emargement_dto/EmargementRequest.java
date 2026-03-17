package com.genus.GENUS_SUP.dto.emargement_dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmargementRequest {
    @NotNull(message = "required field")
    private boolean isPresent;
    @NotNull(message = "required field")
    private Long enseignantId;
    @NotNull(message = "required field")
    private Long planificationId;
}
