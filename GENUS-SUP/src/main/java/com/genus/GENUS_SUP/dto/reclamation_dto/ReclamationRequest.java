package com.genus.GENUS_SUP.dto.reclamation_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReclamationRequest {
    @NotNull(message = "required field")
    private Long noteId;
    @NotEmpty(message = "required field")
    private String motif;

}
