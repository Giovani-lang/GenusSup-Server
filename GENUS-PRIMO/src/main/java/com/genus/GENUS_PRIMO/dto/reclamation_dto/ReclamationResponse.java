package com.genus.GENUS_PRIMO.dto.reclamation_dto;

import com.genus.GENUS_PRIMO.dto.note_dto.NoteResponse;
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
