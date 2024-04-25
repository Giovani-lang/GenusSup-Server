package com.logonedigital.PI.SCHULE.dto.cycle_dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CycleRequest {
    @NotEmpty(message = "required field")
    private String nom;
    private Long ecoleId;
}
