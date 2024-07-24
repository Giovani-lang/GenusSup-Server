package com.genus.GENUS_PRIMO.dto.classe_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseRequest {
    @NotEmpty(message = "required field")
    private String nom;
    @NotNull(message = "required field")
    private int niveau;
    @NotNull(message = "required field")
    private Long cycleId;
    private boolean isDeleted;
}
