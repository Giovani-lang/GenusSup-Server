package com.genus.GENUS_SUP.dto.salaireConfig_dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaireConfigRequest {
    @NotNull(message = "required field")
    private Long ecoleId;
    @NotNull(message = "required field")
    private int jour;
}
