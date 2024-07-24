package com.genus.GENUS_PRIMO.dto.matiere_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatiereRequest {
    @NotEmpty(message = "required field")
    private String module,code,intitule;
    @NotNull(message = "required field")
    private int coefficient;
    @NotNull(message = "required field")
    private Long optionId;
    private Long enseignantId;
    private boolean isDeleted;
}
