package com.logonedigital.PI.SCHULE.dto.superAdmin_dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperAdminRequest {
    @NotEmpty(message = "Required field")
    private String prenom, nom,email,telephone,password,genre;
    private String status;
    private String image_url;
}
