package com.genus.GENUS_SUP.Controller;


import com.genus.GENUS_SUP.Service.Interface.IMatiereService;
import com.genus.GENUS_SUP.dto.matiere_dto.MatiereRequest;
import com.genus.GENUS_SUP.dto.matiere_dto.MatiereResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/matieres")
public class MatiereController {

    private final IMatiereService matiereService;

    @PostMapping("/add")
    public ResponseEntity<MatiereResponse> addMatiere(@RequestBody @Valid MatiereRequest matiere){
        return new ResponseEntity<>(this.matiereService.addMatiere(matiere), HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<MatiereResponse>> getAllMatiere(){
        return new ResponseEntity<>(this.matiereService.getMatiere(), HttpStatus.OK);
    }

    @GetMapping("/findAll/{ecoleId}")
    public ResponseEntity<List<MatiereResponse>> getAllMatiereByEcole(@PathVariable(name = "ecoleId") Long ecoleId){
        return new ResponseEntity<>(this.matiereService.getMatiereByEcole(ecoleId), HttpStatus.OK);
    }

    @GetMapping("/getAll/{optionId}")
    public ResponseEntity<List<MatiereResponse>> getAllMatiereByOption(@PathVariable(name = "optionId") Long optionId){
        return new ResponseEntity<>(this.matiereService.getMatiereByOption(optionId), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<MatiereResponse> updateMatiere(@PathVariable(name = "id")Long id,@RequestBody MatiereRequest matiere){
        return new ResponseEntity<>(this.matiereService.updateMatiere(id, matiere), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/delete/{id}")
    public ResponseEntity<MatiereResponse> deleteMatiere(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.matiereService.deleteMatiere(id), HttpStatus.ACCEPTED);
    }
}
