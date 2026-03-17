package com.genus.GENUS_SUP.Controller;


import com.genus.GENUS_SUP.Service.Interface.IOptionService;
import com.genus.GENUS_SUP.dto.option_dto.OptionRequest;
import com.genus.GENUS_SUP.dto.option_dto.OptionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/options")
public class OptionController {

    private final IOptionService optionService;

    @PostMapping("/add")
    public ResponseEntity<OptionResponse> addOption(@RequestBody @Valid OptionRequest option){
        return new ResponseEntity<>(this.optionService.addOption(option), HttpStatus.CREATED);
    }
    @GetMapping("/findAll/{ecoleId}")
    public ResponseEntity<List<OptionResponse>> getAllOption(@PathVariable(name = "ecoleId") Long ecoleId){
        return new ResponseEntity<>(this.optionService.getOptionByEcole(ecoleId), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<OptionResponse> getOption(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(this.optionService.getOption(id), HttpStatus.OK);
    }

    @GetMapping("/getAll/{classeId}")
    public ResponseEntity<List<OptionResponse>> getAllOptionByClasse(@PathVariable(name = "classeId") Long classeId){
        return new ResponseEntity<>(this.optionService.getOptionByClasse(classeId), HttpStatus.OK);
    }
    @GetMapping("/getAllForTeacher/{ensEmail}")
    public ResponseEntity<List<OptionResponse>> getAllOptionForTeacher(@PathVariable(name = "ensEmail") String ensEmail){
        return new ResponseEntity<>(this.optionService.getOptionForTeacher(ensEmail), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<OptionResponse> updateOption(@PathVariable(name = "id")Long id,@RequestBody @Valid  OptionRequest option){
        return new ResponseEntity<>(this.optionService.updateOption(id, option), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/delete/{id}")
    public ResponseEntity<OptionResponse> deleteOption(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.optionService.deleteOption(id), HttpStatus.ACCEPTED);
    }
}
