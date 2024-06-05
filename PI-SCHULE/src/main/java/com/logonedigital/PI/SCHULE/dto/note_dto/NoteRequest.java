package com.logonedigital.PI.SCHULE.dto.note_dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {

    @NotNull(message = "required field")
    private Long matiereId,etudiantId,anneeAcademiqueId;
    @NotEmpty(message ="required field")
    private String periode;
    private Float pondCC,pondSN,pondRT;
    @Min(value = 0,message = "you can't enter a value above 0")
    @Max(value = 20,message = "you can't exceed 20")
    private Float noteControle,noteSession,noteRattrapage;
}
