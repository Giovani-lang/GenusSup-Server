package com.genus.GENUS_PRIMO.dto.reclamation_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReclamationTreated {
    @NotNull(message = "required field")
    private Long id;
    @NotEmpty(message = "required field")
    private String resolution;
    private String status;
}
