package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IDevoirService;
import com.genus.GENUS_SUP.dto.devoir_dto.DevoirRequest;
import com.genus.GENUS_SUP.dto.devoir_dto.DevoirResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/devoirs")
@RequiredArgsConstructor
public class DevoirController {
    private final IDevoirService devoirService;

    @PostMapping("/add")
    public ResponseEntity<DevoirResponse> addDevoir(@RequestBody DevoirRequest devoirRequest){
        return new ResponseEntity<>(this.devoirService.addDevoir(devoirRequest), HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<DevoirResponse> editDevoir(@PathVariable(name = "id") Long id,@RequestBody DevoirRequest devoirRequest){
        return new ResponseEntity<>(this.devoirService.editDevoir(id,devoirRequest), HttpStatus.ACCEPTED);
    }
    @GetMapping("/findByEtudiant/{anneeId}/{optionId}")
    public ResponseEntity<List<DevoirResponse>> findByEtudiant(@PathVariable(name = "anneeId") Long anneeId,
                                                               @PathVariable(name = "optionId") Long optionId){
        return new ResponseEntity<>(this.devoirService.listDevoirByEtudiant(anneeId,optionId), HttpStatus.OK);
    }
    @GetMapping("/findByOption/{optionId}")
    public ResponseEntity<List<DevoirResponse>> findByOption(@PathVariable(name = "optionId") Long optionId){
        return new ResponseEntity<>(this.devoirService.listDevoirByOption(optionId), HttpStatus.OK);
    }
}
