package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Service.Interface.INoteService;
import com.genus.GENUS_SUP.dto.note_dto.NoteRequest;
import com.genus.GENUS_SUP.dto.note_dto.NoteResponse;
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

    @GetMapping("/getAll/{ecoleId}")
    public ResponseEntity<List<NoteResponse>> getNotes(@PathVariable(name = "ecoleId")Long ecoleId){
        return new ResponseEntity<>(this.noteService.getNotes(ecoleId),HttpStatus.OK);
    }
    @GetMapping("/getOptionMarks/{optionId}")
    public ResponseEntity<List<NoteResponse>> getOptionMarks(@PathVariable(name = "optionId")Long optionId){
        return new ResponseEntity<>(this.noteService.getOptionMarks(optionId),HttpStatus.OK);
    }
    @GetMapping("/getStudentMarks/{matricule}/{optionId}")
    public ResponseEntity<List<NoteResponse>> findNotes(
            @PathVariable(name = "matricule") String matricule,
            @PathVariable(name = "optionId") Long optionId
    ){
        return new ResponseEntity<>(this.noteService.findNotes(matricule, optionId),HttpStatus.OK);
    }

    @GetMapping("/getMarksOfSubject/{matiereId}")
    public ResponseEntity<List<NoteResponse>> getMarksOfSubject(
            @PathVariable(name = "matiereId") Long matiereId
    ){
        return new ResponseEntity<>(this.noteService.getMarksOfSubject(matiereId),HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<NoteResponse> updateNote(@PathVariable(name = "id") Long id,
                                                   @Valid  @RequestBody NoteRequest note) throws RessourceNotFoundException{
        return new ResponseEntity<>(this.noteService.updateNote(id, note),HttpStatus.ACCEPTED);
    }

}
