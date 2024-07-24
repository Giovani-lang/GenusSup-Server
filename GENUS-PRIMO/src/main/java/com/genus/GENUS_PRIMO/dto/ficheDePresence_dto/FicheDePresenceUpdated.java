package com.genus.GENUS_PRIMO.dto.ficheDePresence_dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FicheDePresenceUpdated {
    private Long id;
    @NotNull(message = "required field")
    private boolean isAbsent;
}
