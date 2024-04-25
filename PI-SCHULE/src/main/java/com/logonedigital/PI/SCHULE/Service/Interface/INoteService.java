package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.Note;
import com.logonedigital.PI.SCHULE.dto.note_dto.NoteRequest;
import com.logonedigital.PI.SCHULE.dto.note_dto.NoteResponse;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface INoteService {

    NoteResponse addNote(NoteRequest noteRequest);
    NoteResponse getNote(Long id);
    List<NoteResponse> getNotes();
    List<NoteResponse> findNotes(String matricule, Long optionId);
    List<NoteResponse> findNotesByYear(String matricule, Long optionId,Long anneeAcademiqueId);
    NoteResponse updateNote(Long id,NoteRequest noteRequest);
}
