package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Note;
import com.genus.GENUS_SUP.Entity.Reclamation;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.ReclamationMapper;
import com.genus.GENUS_SUP.Repository.NoteRepository;
import com.genus.GENUS_SUP.Repository.ReclamationRepository;
import com.genus.GENUS_SUP.Service.Interface.IReclamationService;
import com.genus.GENUS_SUP.dto.reclamation_dto.ReclamationRequest;
import com.genus.GENUS_SUP.dto.reclamation_dto.ReclamationResponse;
import com.genus.GENUS_SUP.dto.reclamation_dto.ReclamationTreated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReclamationService implements IReclamationService {
    private final ReclamationRepository reclamationRepo;
    private final ReclamationMapper reclamationMapper;
    private final NoteRepository noteRepo;
    private final EmailService emailService;

    @Override
    public ReclamationResponse addReclamation(ReclamationRequest reclamationRequest) {
        Reclamation reclamation = this.reclamationMapper.fromReclamationRequest(reclamationRequest);
        reclamation.setClaimedAt(new Date());
        Note note = this.noteRepo.findById(reclamationRequest.getNoteId())
                .orElseThrow(()-> new RessourceNotFoundException("This ID "+reclamationRequest.getNoteId()+" doesn't exist"));
        reclamation.setNote(note);
        reclamation.setStatus("En attente");

        Reclamation claimSaved = this.reclamationRepo.save(reclamation);
//        this.emailService.sendUserNotificationEmail(claimSaved.getNote().getMatiere().getEnseignant());
        return this.reclamationMapper.fromReclamation(claimSaved);
    }

    @Override
    public ReclamationResponse treatReclamation(ReclamationTreated reclamationTreated) {
        Reclamation reclamationUpdated = this.reclamationRepo.findById(reclamationTreated.getId())
                .orElseThrow(()-> new RessourceNotFoundException("This ID :"+reclamationTreated.getId()+"doesn't match with a reclamation"));
        Reclamation reclamation = this.reclamationMapper.fromReclamationTreated(reclamationTreated);
        reclamationUpdated.setResolution(reclamation.getResolution());
        reclamationUpdated.setStatus(reclamation.getStatus());
        reclamationUpdated.setTreatedAt(new Date());

        Reclamation claimTreated = this.reclamationRepo.save(reclamationUpdated);
//        this.emailService.sendUserNotificationEmail(claimTreated.getNote().getEtudiant());
        return this.reclamationMapper.fromReclamation(claimTreated);
    }

    @Override
    public ReclamationResponse getReclamation(Long id) {
        return this.reclamationMapper.fromReclamation(this.reclamationRepo.findByNote(id)
                .orElseThrow(()-> new RessourceNotFoundException("This ID :"+id+"doesn't match with a reclamation")));
    }

    @Override
    public List<ReclamationResponse> findByEcole(Long ecoleId) {
        List<Reclamation> reclamations =  this.reclamationRepo.findByEcole(ecoleId);
        List<ReclamationResponse> reclamationResponses = new ArrayList<>();
        reclamations.forEach(reclamation -> reclamationResponses.add(this.reclamationMapper
                .fromReclamation(reclamation)));
        return reclamationResponses;
    }

    @Override
    public List<ReclamationResponse> findByEnseignant(Long ensId) {
        List<Reclamation> reclamations =  this.reclamationRepo.findByEnseignant(ensId);
        List<ReclamationResponse> reclamationResponses = new ArrayList<>();
        reclamations.forEach(reclamation -> reclamationResponses.add(this.reclamationMapper
                .fromReclamation(reclamation)));
        return reclamationResponses;
    }

    @Override
    public List<ReclamationResponse> findByEtudiant(Long etdId) {
        List<Reclamation> reclamations =  this.reclamationRepo.findByEtudiant(etdId);
        List<ReclamationResponse> reclamationResponses = new ArrayList<>();
        reclamations.forEach(reclamation -> reclamationResponses.add(this.reclamationMapper
                .fromReclamation(reclamation)));
        return reclamationResponses;
    }
}
