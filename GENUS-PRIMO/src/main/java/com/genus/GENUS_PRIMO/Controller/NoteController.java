package com.genus.GENUS_PRIMO.Controller;

import com.genus.GENUS_PRIMO.Exception.RessourceExistException;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Service.Interface.INoteService;
import com.genus.GENUS_PRIMO.dto.note_dto.NoteRequest;
import com.genus.GENUS_PRIMO.dto.note_dto.NoteResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final INoteService noteService;

    public NoteController(INoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public ResponseEntity<NoteResponse> addNote(@RequestBody @Valid NoteRequest note)throws RessourceExistException {
        return new ResponseEntity<>(this.noteService.addNote(note), HttpStatus.CREATED);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<NoteResponse> getNote(@PathVariable(name = "id") Long id) throws RessourceNotFoundException {
        return new ResponseEntity<>(this.noteService.getNote(id),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<NoteResponse>> getNotes(){
        return new ResponseEntity<>(this.noteService.getNotes(),HttpStatus.OK);
    }
    @GetMapping("/getAll/{matricule}/{optionId}")
    public ResponseEntity<List<NoteResponse>> findNotes(
            @PathVariable(name = "matricule") String matricule,
            @PathVariable(name = "optionId") Long optionId
    ){
        return new ResponseEntity<>(this.noteService.findNotes(matricule, optionId),HttpStatus.OK);
    }
    @GetMapping("/getAll/{matricule}/{optionId}/{anneeAcademiqueId}")
    public ResponseEntity<List<NoteResponse>> findNotesByYear(
            @PathVariable(name = "matricule") String matricule,
            @PathVariable(name = "optionId") Long optionId,
            @PathVariable(name = "anneeAcademiqueId") Long anneeAcademiqueId
    ){
        return new ResponseEntity<>(this.noteService.findNotesByYear(matricule, optionId,anneeAcademiqueId),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable(name = "id") Long id,
                                                   @Valid  @RequestBody NoteRequest note) throws RessourceNotFoundException{
        return new ResponseEntity<>(this.noteService.updateNote(id, note),HttpStatus.ACCEPTED);
    }

}
