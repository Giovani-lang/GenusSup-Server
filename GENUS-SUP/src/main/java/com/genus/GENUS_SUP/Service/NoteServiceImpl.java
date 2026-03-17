package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.AnneeAcademique;
import com.genus.GENUS_SUP.Entity.Etudiant;
import com.genus.GENUS_SUP.Entity.Matiere;
import com.genus.GENUS_SUP.Entity.Note;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.NoteMapper;
import com.genus.GENUS_SUP.Repository.AnneeAcademiqueRepository;
import com.genus.GENUS_SUP.Repository.EtudiantRepository;
import com.genus.GENUS_SUP.Repository.MatiereRepository;
import com.genus.GENUS_SUP.Repository.NoteRepository;
import com.genus.GENUS_SUP.Service.Interface.INoteService;
import com.genus.GENUS_SUP.dto.note_dto.NoteRequest;
import com.genus.GENUS_SUP.dto.note_dto.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements INoteService {

    private final NoteRepository noteRepo;
    private final NoteMapper noteMapper;
    private final MatiereRepository matiereRepo;
    private final EtudiantRepository etudiantRepo;
    private final AnneeAcademiqueRepository anneeAcademiqueRepo;

    @Override
    public NoteResponse addNote(NoteRequest noteRequest){
        Note note = this.noteMapper.fromNoteRequest(noteRequest);
        // Verification si la note insérée existe déjà pour la periode renseignée
        Optional<Note> checkingNote = this.noteRepo.getExistingMark(
                noteRequest.getEtudiantId(),
                noteRequest.getMatiereId(),
                noteRequest.getPeriode());
        if(checkingNote.isPresent()){
            throw new RessourceExistException("Already exist");
        }

        Matiere matiere = this.matiereRepo.findById(noteRequest.getMatiereId())
                .orElseThrow(()->new RessourceNotFoundException("Not found"));
        note.setMatiere(matiere);
        Etudiant etudiant = this.etudiantRepo.findById(noteRequest.getEtudiantId())
                .orElseThrow(()->new RessourceNotFoundException("Not found"));
        note.setEtudiant(etudiant);
        AnneeAcademique anneeAcademique = this.anneeAcademiqueRepo.findById(noteRequest.getAnneeAcademiqueId())
                .orElseThrow(()->new RessourceNotFoundException("Not found"));
        note.setAnneeAcademique(anneeAcademique);

        Note noteSaved = this.noteRepo.save(note);
        return this.noteMapper.fromNote(noteSaved);
    }

    @Override
    public List<NoteResponse> getNotes(Long ecoleId) {
        List<Note> notes = this.noteRepo.findByEcole(ecoleId);
        List<NoteResponse> noteResponses = new ArrayList<>();
        notes.forEach(note -> noteResponses.add(this.noteMapper.fromNote(note)));
        return noteResponses;
    }

    @Override
    public List<NoteResponse> getOptionMarks(Long optionId) {
        List<Note> notes = this.noteRepo.getOptionMarks(optionId);
        List<NoteResponse> noteResponses = new ArrayList<>();
        notes.forEach(note -> noteResponses.add(this.noteMapper.fromNote(note)));
        return noteResponses;
    }

    @Override
    public List<NoteResponse> findNotes(String matricule, Long optionId) {
        List<Note> notes = this.noteRepo.findNotes(matricule, optionId);
        List<NoteResponse> noteResponses = new ArrayList<>();
        notes.forEach(note -> noteResponses.add(this.noteMapper.fromNote(note)));
        return noteResponses;
    }

    @Override
    public List<NoteResponse> getMarksOfSubject(Long matiereId) {
        List<Note> notes = this.noteRepo.getMarksOfSubject(matiereId);
        List<NoteResponse> noteResponses = new ArrayList<>();
        notes.forEach(note -> noteResponses.add(this.noteMapper.fromNote(note)));
        return noteResponses;
    }

    @Override
    public NoteResponse updateNote(Long id, NoteRequest noteRequest) throws RessourceNotFoundException {
        try {
            Note newNote = this.noteRepo.findById(id).get();

            Matiere matiere = this.matiereRepo.findById(noteRequest.getMatiereId())
                    .orElseThrow(()->new RessourceNotFoundException("Not found"));
            newNote.setMatiere(matiere);
            AnneeAcademique anneeAcademique = this.anneeAcademiqueRepo.findById(noteRequest.getAnneeAcademiqueId())
                    .orElseThrow(()->new RessourceNotFoundException("Not found"));
            newNote.setAnneeAcademique(anneeAcademique);
            newNote.setNoteControle(noteRequest.getNoteControle());
            newNote.setNoteSession(noteRequest.getNoteSession());
            newNote.setPeriode(noteRequest.getPeriode());
            Note noteUpdated = this.noteRepo.save(newNote);
            return this.noteMapper.fromNote(noteUpdated);
        }catch (NoSuchElementException ex){
            throw new RessourceNotFoundException("Impossible to update");
        }
    }

}
