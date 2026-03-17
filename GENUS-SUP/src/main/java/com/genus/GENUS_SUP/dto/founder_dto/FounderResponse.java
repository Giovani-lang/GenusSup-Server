package com.genus.GENUS_SUP.dto.founder_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FounderResponse {
    private Long id;
    private String nom, prenom,email,telephone,password,genre,role,image_url,status;
    private boolean firstLogin;
    private Date createdAt, updatedAt;
}
