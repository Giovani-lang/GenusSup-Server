package com.logonedigital.PI.SCHULE.dto.login_dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {
    @NotEmpty(message = "required field")
    private String email;
    @NotEmpty(message = "required field")
    private String password;

}
