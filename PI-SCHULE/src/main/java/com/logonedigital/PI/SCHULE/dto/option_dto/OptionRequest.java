package com.logonedigital.PI.SCHULE.dto.option_dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionRequest {
    @NotEmpty(message = "required field")
    private String nom;
    @NotNull(message = "required field")
    private Long classeId;
    private boolean isDeleted;
}
