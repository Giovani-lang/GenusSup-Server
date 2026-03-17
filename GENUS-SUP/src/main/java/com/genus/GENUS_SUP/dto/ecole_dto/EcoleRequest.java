package com.genus.GENUS_SUP.dto.ecole_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcoleRequest {
    private String image_url;
    @NotEmpty(message = "required field")
    private String name,acronym,email,site,address,fax,phone,responsible,responsible_phone,country,city;
    @NotNull(message = "required field")
    private Long po_box;
}
