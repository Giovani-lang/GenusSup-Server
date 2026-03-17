package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IClasseService;
import com.genus.GENUS_SUP.dto.classe_dto.ClasseRequest;
import com.genus.GENUS_SUP.dto.classe_dto.ClasseResponse;
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

    @GetMapping("/getAll/{cycleId}")
    public ResponseEntity<List<ClasseResponse>> getAllClasseBycycle(@PathVariable(name = "cycleId") Long cycleId){
        return new ResponseEntity<>(this.classeService.getClasseByFiliere(cycleId), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<ClasseResponse> updateClasse(@PathVariable(name = "id")Long id,
                                                       @RequestBody @Valid  ClasseRequest classeRequest){
        return new ResponseEntity<>(this.classeService.updateClasse(id, classeRequest), HttpStatus.ACCEPTED);
    }@PatchMapping("/delete/{id}")
    public ResponseEntity<ClasseResponse> deleteClasse(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.classeService.deleteClasse(id), HttpStatus.ACCEPTED);
    }
}
