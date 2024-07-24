package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.note_dto.NoteRequest;
import com.genus.GENUS_PRIMO.dto.note_dto.NoteResponse;

import java.util.List;

public interface INoteService {

    NoteResponse addNote(NoteRequest noteRequest);
    NoteResponse getNote(Long id);
    List<NoteResponse> getNotes();
    List<NoteResponse> findNotes(String matricule, Long optionId);
    List<NoteResponse> findNotesByYear(String matricule, Long optionId,Long anneeAcademiqueId);
    NoteResponse updateNote(Long id,NoteRequest noteRequest);
}
