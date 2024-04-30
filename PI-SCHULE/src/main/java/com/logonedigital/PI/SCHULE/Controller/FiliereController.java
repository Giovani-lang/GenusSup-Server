package com.logonedigital.PI.SCHULE.Controller;

import com.logonedigital.PI.SCHULE.Entity.Filiere;
import com.logonedigital.PI.SCHULE.Service.Interface.IFiliereService;
import com.logonedigital.PI.SCHULE.dto.filiere_dto.FiliereRequest;
import com.logonedigital.PI.SCHULE.dto.filiere_dto.FiliereResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/filieres")
public class FiliereController {

    private final IFiliereService filiereService;

    @PostMapping("/add")
    public ResponseEntity<FiliereResponse> addFiliere(@RequestBody @Valid FiliereRequest filiere){
        return new ResponseEntity<>(this.filiereService.addFiliere(filiere), HttpStatus.CREATED);
    }
    @GetMapping("/findAll/{ecoleId}")
    public ResponseEntity<List<FiliereResponse>> getAllFiliere(@PathVariable(name = "ecoleId") Long ecoleId){
        return new ResponseEntity<>(this.filiereService.getFiliere(ecoleId), HttpStatus.OK);
    }

    @GetMapping("/getAll/{cycleId}")
    public ResponseEntity<List<FiliereResponse>> getAllFiliereByCycle(@PathVariable(name = "cycleId") Long cycleId){
        return new ResponseEntity<>(this.filiereService.getFiliereByCycle(cycleId), HttpStatus.OK);
    }

    @GetMapping("/detail/{nom}")
    public ResponseEntity<FiliereResponse> getByName(@PathVariable (name = "nom")String nom){
        return new ResponseEntity<>(this.filiereService.getByName(nom), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<FiliereResponse> updateFiliere(@PathVariable(name = "id")Long id,
                                                         @RequestBody @Valid  FiliereRequest filiere){
        return new ResponseEntity<>(this.filiereService.updateFiliere(id, filiere), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/delete/{id}")
    public ResponseEntity<FiliereResponse> deleteFiliere(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.filiereService.deleteFiliere(id), HttpStatus.ACCEPTED);
    }
}
