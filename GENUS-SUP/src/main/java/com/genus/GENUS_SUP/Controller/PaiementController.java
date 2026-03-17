package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IPaiementService;
import com.genus.GENUS_SUP.dto.paiement_dto.PaiementRequest;
import com.genus.GENUS_SUP.dto.paiement_dto.PaiementResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/paiements")
public class PaiementController {
    private final IPaiementService paiementService;


    @PostMapping("/add")
    public ResponseEntity<PaiementResponse> addPaiement(@RequestBody @Valid PaiementRequest paiementRequest){
        return new ResponseEntity<>(this.paiementService.addPaiement(paiementRequest), HttpStatus.CREATED);
    }

    @GetMapping("/detail/{etudiantId}/{anneeId}")
    public ResponseEntity<List<PaiementResponse>> findPaiementByMatricule(
                @PathVariable(name = "etudiantId")Long etudiantId,
                @PathVariable(name = "anneeId")Long anneeId
    ){
        return new ResponseEntity<>(this.paiementService.getPaiement(etudiantId,anneeId), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PaiementResponse>findPaiementById(@PathVariable(name="id")Long id){
        return new ResponseEntity<>(this.paiementService.getById(id), HttpStatus.OK);
    }

}
