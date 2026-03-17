package com.genus.GENUS_SUP.dto.cycle_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CycleRequest {
    @NotEmpty(message = "required field")
    private String nom,section;
    @NotNull(message = "required field")
    private Long departementId;
    private boolean isDeleted;
}
