package com.genus.GENUS_PRIMO.Controller;

import com.genus.GENUS_PRIMO.Exception.RessourceExistException;
import com.genus.GENUS_PRIMO.Service.Interface.IFicheDePresenceService;
import com.genus.GENUS_PRIMO.dto.ficheDePresence_dto.FicheDePresenceRequest;
import com.genus.GENUS_PRIMO.dto.ficheDePresence_dto.FicheDePresenceResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/ficheDePresence")
@RestController
public class FicheDePresenceController {
    private final IFicheDePresenceService ficheDePresenceService;

    public FicheDePresenceController(IFicheDePresenceService ficheDePresenceService) {
        this.ficheDePresenceService = ficheDePresenceService;
    }

    @PostMapping("/add")
    public ResponseEntity<List<FicheDePresenceResponse>> addFicheDePresence(@RequestBody @Valid List<FicheDePresenceRequest> absence) throws RessourceExistException {
        return new ResponseEntity<>(this.ficheDePresenceService.addFicheDePresence(absence), HttpStatus.CREATED);
    }
    @PostMapping("/justify")
    public ResponseEntity<FicheDePresenceResponse> addJustification(@RequestBody @Valid FicheDePresenceRequest absence) throws RessourceExistException {
        return new ResponseEntity<>(this.ficheDePresenceService.addJustification(absence), HttpStatus.CREATED);
    }

    @GetMapping("/getAll/{ecoleId}")
    public ResponseEntity<List<FicheDePresenceResponse>> getFichesDePresence(
            @PathVariable(name = "ecoleId") Long ecoleId){
        return new ResponseEntity<>(this.ficheDePresenceService.getFichesDePresence(ecoleId),HttpStatus.OK);
    }

    @GetMapping("/findByEtd/{email}")
    public ResponseEntity<List<FicheDePresenceResponse>> getEtudiantList(
            @PathVariable(name = "email") String email
    ){
        return new ResponseEntity<>(this.ficheDePresenceService.getEtudiantList(email),HttpStatus.OK);
    }
}
