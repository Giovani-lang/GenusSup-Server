package com.genus.GENUS_SUP.dto.campus_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CampusRequest {
    @NotEmpty(message = "Required field")
    private String name,address;
    @NotNull(message = "required field")
    private Long ecoleId,responsibleId;
    private boolean isDeleted;
}
