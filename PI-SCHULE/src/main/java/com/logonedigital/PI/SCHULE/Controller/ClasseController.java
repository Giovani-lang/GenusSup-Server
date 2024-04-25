package com.logonedigital.PI.SCHULE.Controller;

import com.logonedigital.PI.SCHULE.Entity.Classe;
import com.logonedigital.PI.SCHULE.Service.Interface.IClasseService;
import com.logonedigital.PI.SCHULE.dto.classe_dto.ClasseRequest;
import com.logonedigital.PI.SCHULE.dto.classe_dto.ClasseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/classes")
public class ClasseController {
    private final IClasseService classeService;

    @PostMapping("/add")
    public ResponseEntity<ClasseResponse> addClasse(@RequestBody @Valid ClasseRequest classe){
        return new ResponseEntity<>(this.classeService.addClasse(classe), HttpStatus.CREATED);
    }
    @GetMapping("/findAll/{ecoleId}")
    public ResponseEntity<List<ClasseResponse>> getAllClasse(@PathVariable(name = "ecoleId") Long ecoleId){
        return new ResponseEntity<>(this.classeService.getClasse(ecoleId), HttpStatus.OK);
    }

    @GetMapping("/getAll/{filiereId}")
    public ResponseEntity<List<ClasseResponse>> getAllClasseByFiliere(@PathVariable(name = "filiereId") Long filiereId){
        return new ResponseEntity<>(this.classeService.getClasseByFiliere(filiereId), HttpStatus.OK);
    }
    @PutMapping("/edit/{nom}")
    public ResponseEntity<ClasseResponse> updateClasse(@PathVariable(name = "nom")String nom,
                                                       @RequestBody @Valid  ClasseRequest classeRequest){
        return new ResponseEntity<>(this.classeService.updateClasse(nom, classeRequest), HttpStatus.ACCEPTED);
    }
}
