package com.genus.GENUS_SUP.dto.founder_dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FounderRequest {
    @NotEmpty(message = "Required field")
    private String prenom, nom,email,telephone,password,genre;
    private String status;
    private String image_url;
}
