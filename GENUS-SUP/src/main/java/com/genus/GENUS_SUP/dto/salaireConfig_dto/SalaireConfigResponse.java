package com.genus.GENUS_SUP.dto.salaireConfig_dto;

import com.genus.GENUS_SUP.Model.EcoleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaireConfigResponse {
    private Long id;
    private int jour;
    private EcoleModel ecole;
}
