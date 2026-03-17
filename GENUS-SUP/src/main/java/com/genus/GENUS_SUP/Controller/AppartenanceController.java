package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IAppartenanceService;
import com.genus.GENUS_SUP.dto.appartenance_dto.AppartenanceRequest;
import com.genus.GENUS_SUP.dto.appartenance_dto.AppartenanceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/appartenances")
@RequiredArgsConstructor
public class AppartenanceController {
    private final IAppartenanceService appartenanceService;

    @PostMapping("/add")
    public ResponseEntity<AppartenanceResponse> addAppartenance(@Valid @RequestBody AppartenanceRequest appartenanceRequest) {
        return new ResponseEntity<>(this.appartenanceService.addAppartenance(appartenanceRequest), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AppartenanceResponse> editAppartenance(@PathVariable(name = "id") Long id,
                                                                 @RequestBody AppartenanceRequest appartenanceRequest) {
        return new ResponseEntity<>(this.appartenanceService.editAppartenance(id, appartenanceRequest),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/getAll/{ecoleId}")
    public ResponseEntity<List<AppartenanceResponse>> getAppartenances(@PathVariable(name = "ecoleId")Long ecoleId) {
        return new ResponseEntity<>(this.appartenanceService.getAllAppartenances(ecoleId), HttpStatus.OK);
    }

    @GetMapping("/getAllByAnnee/{ecoleId}/{anneeAcadimiqueId}")
    public ResponseEntity<List<AppartenanceResponse>> getAppartenancesByanneeAcadimique(
            @PathVariable(name = "ecoleId") Long ecoleId,
            @PathVariable(name = "anneeAcadimiqueId") Long anneeAcadimiqueId) {
        return new ResponseEntity<>(this.appartenanceService.getAllAppartenancesByAnneeAcademique(ecoleId,anneeAcadimiqueId),
                HttpStatus.OK
        );
    }

    @GetMapping("/getByEtudiant/{EtdId}")
    public ResponseEntity<List<AppartenanceResponse>> getAppartenanceByEtudiant(
            @PathVariable(name = "EtdId") Long EtdId) {
        return new ResponseEntity<>(this.appartenanceService.getAllAppartenancesByEtudiant(EtdId), HttpStatus.OK);
    }
    @GetMapping("/getByOption/{anId}/{optId}")
    public ResponseEntity<List<AppartenanceResponse>> getAppartenanceByOptiont(
            @PathVariable(name = "optId") Long optId,@PathVariable(name = "anId") Long anId) {
        return new ResponseEntity<>(this.appartenanceService.getAllAppartenancesByOpiton(anId,optId), HttpStatus.OK);
    }

    @GetMapping("/getByEtudiantAndAnnee/{EtdId}/{AnId}")
    public ResponseEntity<AppartenanceResponse> getAppartenanceByEtudiantAndAnnee(
            @PathVariable(name = "EtdId") Long EtdId,@PathVariable(name = "AnId") Long AnId) {
        return new ResponseEntity<>(this.appartenanceService.getAppartenancesByEtudiantAndAnneeAcademique(
                EtdId, AnId
        ),
                HttpStatus.OK
        );
    }
}
