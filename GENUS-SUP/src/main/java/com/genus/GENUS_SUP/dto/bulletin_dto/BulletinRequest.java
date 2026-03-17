package com.genus.GENUS_SUP.dto.bulletin_dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BulletinRequest {
    @NotNull(message = "required field")
    private Long enseignantId;
    @NotNull(message = "required field")
    private Double montant;
    @NotNull(message = "required field")
    private Long anneeAcademiqueId;
}
