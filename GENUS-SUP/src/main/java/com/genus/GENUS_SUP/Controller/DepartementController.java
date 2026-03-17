package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IDepartementService;
import com.genus.GENUS_SUP.dto.departement_dto.DepartementRequest;
import com.genus.GENUS_SUP.dto.departement_dto.DepartementResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departements")
@RequiredArgsConstructor
public class DepartementController {
    private final IDepartementService departementService;

    @PostMapping("/add")
    public ResponseEntity<DepartementResponse> addDepartement(@RequestBody @Valid DepartementRequest departementRequest){
        return new ResponseEntity<>(this.departementService.addDepartement(departementRequest), HttpStatus.CREATED);
    }
    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<DepartementResponse>> getAllDepartement(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(this.departementService.getDepartement(id), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<DepartementResponse> updateDepartement(@PathVariable(name = "id")Long id,
                                                     @RequestBody @Valid  DepartementRequest departementRequest){
        return new ResponseEntity<>(this.departementService.editDepartement(id, departementRequest), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/delete/{id}")
    public ResponseEntity<DepartementResponse> deleteDepartement(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.departementService.deleteDepartement(id), HttpStatus.ACCEPTED);
    }
}
