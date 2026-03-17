package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Note;
import com.genus.GENUS_SUP.dto.note_dto.NoteRequest;
import com.genus.GENUS_SUP.dto.note_dto.NoteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    Note fromNoteRequest (NoteRequest noteRequest);
    @Mapping(source = "etudiant", target = "etudiant")
    @Mapping(source = "matiere", target = "matiere")
    @Mapping(source = "anneeAcademique", target = "anneeAcademique")
    NoteResponse fromNote (Note note);
}
