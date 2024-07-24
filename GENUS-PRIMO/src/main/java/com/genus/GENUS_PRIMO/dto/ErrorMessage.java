package com.genus.GENUS_PRIMO.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class ErrorMessage {
    private Date date;
    private Integer statusCode;
    private String error;
    private String message;
}
