package com.genus.GENUS_SUP.dto.tranche_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrancheRequest {
    @NotNull(message = "required field")
    private Long numero;
    @NotNull(message = "required field")
    private double montant;
    @NotEmpty(message = "required field")
    private String date;
}
