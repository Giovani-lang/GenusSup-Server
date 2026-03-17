package com.genus.GENUS_SUP.dto.departement_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartementRequest {
    @NotEmpty(message = "Required field")
    private String nom;
    @NotNull(message = "required field")
    private Long campusId, chiefId;
}
