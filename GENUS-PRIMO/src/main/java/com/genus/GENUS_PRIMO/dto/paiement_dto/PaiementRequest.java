package com.genus.GENUS_PRIMO.dto.paiement_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaiementRequest {
    @NotNull(message = "required field")
    private Long etudiantId;
    @NotEmpty(message = "required field")
    private String libelle;
    @NotNull(message = "required field")
    private Double montant;
    @NotNull(message = "required field")
    private Long anneeAcademiqueId;
}
