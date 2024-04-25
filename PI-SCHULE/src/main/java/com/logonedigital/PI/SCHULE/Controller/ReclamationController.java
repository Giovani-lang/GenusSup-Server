package com.logonedigital.PI.SCHULE.Controller;

import com.logonedigital.PI.SCHULE.Service.Interface.IReclamationService;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationRequest;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationResponse;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationTreated;
import com.logonedigital.PI.SCHULE.dto.superAdmin_dto.SuperAdminResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reclamations")
@RequiredArgsConstructor
public class ReclamationController {
    private final IReclamationService reclamationService;

    @PostMapping("/add")
    public ResponseEntity<ReclamationResponse> addReclamation(@Valid @RequestBody ReclamationRequest reclamationRequest) {
        return new ResponseEntity<>(this.reclamationService.addReclamation(reclamationRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/treated")
    public ResponseEntity<ReclamationResponse> editReclamation(@RequestBody ReclamationTreated reclamationTreated) {
        return new ResponseEntity<>(this.reclamationService.treatReclamation(reclamationTreated),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/getReclamation/{noteId}")
    public ResponseEntity<ReclamationResponse> getReclamation(@PathVariable(name = "noteId")Long noteId) {
        return new ResponseEntity<>(this.reclamationService.getReclamation(noteId), HttpStatus.OK);
    }
    @GetMapping("/findByEcole/{ecoleId}")
    public ResponseEntity<List<ReclamationResponse>> findByEcole(@PathVariable(name = "ecoleId")Long ecoleId) {
        return new ResponseEntity<>(this.reclamationService.findByEcole(ecoleId), HttpStatus.OK);
    }
    @GetMapping("/findByEnseignant/{ensId}")
    public ResponseEntity<List<ReclamationResponse>> findByEnseignant(@PathVariable(name = "ensId")Long ensId) {
        return new ResponseEntity<>(this.reclamationService.findByEnseignant(ensId), HttpStatus.OK);
    }
    @GetMapping("/findByEtudiant/{etdId}")
    public ResponseEntity<List<ReclamationResponse>> findByEtudiant(@PathVariable(name = "etdId")Long etdId) {
        return new ResponseEntity<>(this.reclamationService.findByEtudiant(etdId), HttpStatus.OK);
    }
}
