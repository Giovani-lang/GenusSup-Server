package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Service.Interface.IEmargementService;
import com.genus.GENUS_SUP.dto.emargement_dto.EmargementRequest;
import com.genus.GENUS_SUP.dto.emargement_dto.EmargementResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/emargements")
@RestController
@RequiredArgsConstructor
public class EmargementController {
    private final IEmargementService emargementService;


    @PostMapping("/add")
    public ResponseEntity<EmargementResponse> addEmargement(@RequestBody @Valid EmargementRequest emargementRequest)
            throws RessourceExistException {
        return new ResponseEntity<>(this.emargementService.addEmargement(emargementRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAll/{ecoleId}")
    public ResponseEntity<List<EmargementResponse>> getAllEmargements(
            @PathVariable(name = "ecoleId") Long ecoleId){
        return new ResponseEntity<>(this.emargementService.getAllEmargement(ecoleId),HttpStatus.OK);
    }

    @GetMapping("/findByEns/{email}")
    public ResponseEntity<List<EmargementResponse>> getEnseignantList(
            @PathVariable(name = "email") String email
    ){
        return new ResponseEntity<>(this.emargementService.getEnseignantList(email),HttpStatus.OK);
    }
}
