package com.logonedigital.PI.SCHULE.dto.reclamation_dto;

import com.logonedigital.PI.SCHULE.dto.note_dto.NoteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReclamationResponse {
    private Long id;
    private String motif;
    private String resolution;
    private NoteResponse note;
    private String status;
    private Date claimedAt, treatedAt;
}
