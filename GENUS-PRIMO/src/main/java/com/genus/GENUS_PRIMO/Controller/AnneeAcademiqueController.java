package com.genus.GENUS_PRIMO.Controller;

import com.genus.GENUS_PRIMO.Service.Interface.IAnneeAcademiqueService;
import com.genus.GENUS_PRIMO.dto.anneeAcademique_dto.AnneeAcademiqueRequest;
import com.genus.GENUS_PRIMO.dto.anneeAcademique_dto.AnneeAcademiqueResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/anneeAcademique")
public class AnneeAcademiqueController {

    private final IAnneeAcademiqueService anneeAcademiqueService;

    @PostMapping("/add")
    public ResponseEntity<AnneeAcademiqueResponse> addAnnee(@RequestBody @Valid AnneeAcademiqueRequest anneeAcademiqueRequest){
        return new ResponseEntity<>(this.anneeAcademiqueService.addAnnee(anneeAcademiqueRequest), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<AnneeAcademiqueResponse> editAnnee(@PathVariable(name = "id") Long id,
                                                     @RequestBody @Valid  AnneeAcademiqueRequest anneeAcademiqueRequest){
        return new ResponseEntity<>(this.anneeAcademiqueService.editAnnee(id,anneeAcademiqueRequest),HttpStatus.ACCEPTED);
    }

    @GetMapping("getAll/{ecoleId}")
    public  ResponseEntity<List<AnneeAcademiqueResponse>> findAll(@PathVariable(name = "ecoleId")Long ecoleId){
        return new ResponseEntity<>(this.anneeAcademiqueService.getAllAnnee(ecoleId),HttpStatus.OK);
    }
}
