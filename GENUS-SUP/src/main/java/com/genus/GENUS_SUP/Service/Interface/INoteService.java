package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.note_dto.NoteRequest;
import com.genus.GENUS_SUP.dto.note_dto.NoteResponse;

import java.util.List;

public interface INoteService {


    NoteResponse addNote(NoteRequest noteRequest);
    List<NoteResponse> getNotes(Long ecoleId);
    List<NoteResponse> getOptionMarks(Long optionId);
    List<NoteResponse> findNotes(String matricule, Long optionId);
    List<NoteResponse> getMarksOfSubject(Long matiereId);
    NoteResponse updateNote(Long id,NoteRequest noteRequest);
}
