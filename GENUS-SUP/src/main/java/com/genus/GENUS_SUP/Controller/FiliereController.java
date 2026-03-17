package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IFiliereService;
import com.genus.GENUS_SUP.dto.filiere_dto.FiliereRequest;
import com.genus.GENUS_SUP.dto.filiere_dto.FiliereResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/filieres")
@RequiredArgsConstructor
public class FiliereController {
    private final IFiliereService filiereService;

    @PostMapping("/add")
    public ResponseEntity<FiliereResponse> addFiliere(@RequestBody @Valid FiliereRequest filiereRequest){
        return new ResponseEntity<>(this.filiereService.addFiliere(filiereRequest), HttpStatus.CREATED);
    }
    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<FiliereResponse>> getAllFiliere(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(this.filiereService.getFiliere(id), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<FiliereResponse> upadateFiliere(@PathVariable(name = "id")Long id,
                                                     @RequestBody @Valid  FiliereRequest filiereRequest){
        return new ResponseEntity<>(this.filiereService.editFiliere(id, filiereRequest), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/delete/{id}")
    public ResponseEntity<FiliereResponse> deleteFiliere(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.filiereService.deleteFiliere(id), HttpStatus.ACCEPTED);
    }
}
