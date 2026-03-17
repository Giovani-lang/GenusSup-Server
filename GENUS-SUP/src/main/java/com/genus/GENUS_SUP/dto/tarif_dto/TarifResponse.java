package com.genus.GENUS_SUP.dto.tarif_dto;

import com.genus.GENUS_SUP.Model.OptionModel;
import com.genus.GENUS_SUP.Model.TrancheModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarifResponse {
    private Long id;
    private double montant;
    private OptionModel option;
    private List<TrancheModel> tranches;
}
