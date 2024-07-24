package com.genus.GENUS_PRIMO.dto.tarif_dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifRequest {
    @NotNull(message = "required field")
    private double montant;
    @NotNull(message = "required field")
    private Long optionId;
    private boolean isDeleted;
}
