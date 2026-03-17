package com.genus.GENUS_SUP.dto.tarif_dto;

import com.genus.GENUS_SUP.dto.tranche_dto.TrancheRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifRequest {
    @NotNull(message = "required field")
    private double montant;
    @NotNull(message = "required field")
    private Long optionId;
    @NotEmpty(message = "required fields")
    private List<TrancheRequest> tranches;
    private boolean isDeleted;
}
