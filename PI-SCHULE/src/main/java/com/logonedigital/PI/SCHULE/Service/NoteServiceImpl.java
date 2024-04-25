package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.AnneeAcademique;
import com.logonedigital.PI.SCHULE.Entity.Etudiant;
import com.logonedigital.PI.SCHULE.Entity.Matiere;
import com.logonedigital.PI.SCHULE.Entity.Note;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.NoteMapper;
import com.logonedigital.PI.SCHULE.Repository.AnneeAcademiqueRepository;
import com.logonedigital.PI.SCHULE.Repository.EtudiantRepository;
import com.logonedigital.PI.SCHULE.Repository.MatiereRepository;
import com.logonedigital.PI.SCHULE.Repository.NoteRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.INoteService;
import com.logonedigital.PI.SCHULE.dto.note_dto.NoteRequest;
import com.logonedigital.PI.SCHULE.dto.note_dto.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements INoteService {

    private final NoteRepository noteRepo;
    private final NoteMapper noteMapper;
    private final MatiereRepository matiereRepo;
    private final EtudiantRepository etudiantRepo;
    private final AnneeAcademiqueRepository anneeAcademiqueRepo;

    @Override
    public NoteResponse addNote(NoteRequest noteRequest) throws RessourceExistException {
        Note note = this.noteMapper.fromNoteRequest(noteRequest);
        Matiere matiere = this.matiereRepo.findById(noteRequest.getMatiereId())
                .orElseThrow(()->new RessourceNotFoundException("Not found"));
        note.setMatiere(matiere);
        Etudiant etudiant = this.etudiantRepo.findById(noteRequest.getEtudiantId())
                .orElseThrow(()->new RessourceNotFoundException("Not found"));
        note.setEtudiant(etudiant);
        AnneeAcademique anneeAcademique = this.anneeAcademiqueRepo.findById(noteRequest.getAnneeAcademiqueId())
                .orElseThrow(()->new RessourceNotFoundException("Not found"));
        note.setAnneeAcademique(anneeAcademique);

        return this.noteMapper.fromNote(this.noteRepo.save(note));
    }

    @Override
    public NoteResponse getNote(Long id) throws RessourceNotFoundException {
       try {
           return this.noteMapper.fromNote(this.noteRepo.findById(id).get());
       }catch (Exception ex){
           throw new RessourceNotFoundException("Not found");
       }
    }

    @Override
    public List<NoteResponse> getNotes() {
        List<Note> notes = this.noteRepo.findAll();
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
    public List<NoteResponse> findNotesByYear(String matricule, Long optionId, Long anneeAcademiqueId) {
        List<Note> notes = this.noteRepo.findNotesByEtudiantAndYears(matricule, optionId,anneeAcademiqueId);
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
           return this.noteMapper.fromNote(this.noteRepo.save(newNote));
       }catch (NoSuchElementException ex){
           throw new RessourceNotFoundException("Impossible to update");
       }
    }

}
