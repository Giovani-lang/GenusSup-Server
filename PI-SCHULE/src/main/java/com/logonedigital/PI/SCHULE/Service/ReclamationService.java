package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Note;
import com.logonedigital.PI.SCHULE.Entity.Reclamation;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.ReclamationMapper;
import com.logonedigital.PI.SCHULE.Repository.NoteRepository;
import com.logonedigital.PI.SCHULE.Repository.ReclamationRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IReclamationService;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationRequest;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationResponse;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationTreated;
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
    @Override
    public ReclamationResponse addReclamation(ReclamationRequest reclamationRequest) {
        Reclamation reclamation = this.reclamationMapper.fromReclamationRequest(reclamationRequest);
        reclamation.setClaimedAt(new Date());
        Note note = this.noteRepo.findById(reclamationRequest.getNoteId())
                .orElseThrow(()-> new RessourceNotFoundException("This ID "+reclamationRequest.getNoteId()+" doesn't exist"));
        reclamation.setNote(note);
        reclamation.setStatus("En attente");
        return this.reclamationMapper.fromReclamation(this.reclamationRepo.save(reclamation));
    }

    @Override
    public ReclamationResponse treatReclamation(ReclamationTreated reclamationTreated) {
        Reclamation reclamationUpdated = this.reclamationRepo.findById(reclamationTreated.getId())
                .orElseThrow(()-> new RessourceNotFoundException("This ID :"+reclamationTreated.getId()+"doesn't match with a reclamation"));
        Reclamation reclamation = this.reclamationMapper.fromReclamationTreated(reclamationTreated);
        reclamationUpdated.setResolution(reclamation.getResolution());
        reclamationUpdated.setStatus(reclamation.getStatus());
        reclamationUpdated.setTreatedAt(new Date());
        return this.reclamationMapper.fromReclamation(this.reclamationRepo.save(reclamationUpdated));
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
