package com.genus.GENUS_SUP.dto.ficheDePresence_dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheDePresenceRequest {
    @NotNull(message = "required field")
    private boolean isAbsent;
    @NotNull(message = "required field")
    private Long etudiantId;
    @NotNull(message = "required field")
    private Long planificationId;
}
