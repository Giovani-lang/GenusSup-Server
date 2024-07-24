package com.genus.GENUS_PRIMO.dto.tarif_dto;

import com.genus.GENUS_PRIMO.dto.option_dto.OptionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifResponse {
    private Long id;
    private double montant;
    private OptionResponse option;
}
