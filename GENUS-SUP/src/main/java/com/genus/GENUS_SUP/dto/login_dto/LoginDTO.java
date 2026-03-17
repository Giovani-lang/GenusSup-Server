package com.genus.GENUS_SUP.dto.login_dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {
    @NotEmpty(message = "required field")
    private String username;
    @NotEmpty(message = "required field")
    private String password;

}
