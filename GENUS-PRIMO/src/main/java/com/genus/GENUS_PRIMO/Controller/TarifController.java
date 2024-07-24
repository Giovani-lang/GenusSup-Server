package com.genus.GENUS_PRIMO.Controller;

import com.genus.GENUS_PRIMO.Service.Interface.ITarifService;
import com.genus.GENUS_PRIMO.dto.tarif_dto.TarifRequest;
import com.genus.GENUS_PRIMO.dto.tarif_dto.TarifResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tarifs")
@RequiredArgsConstructor
public class TarifController {
    private final ITarifService tarifService;

    @PostMapping("/add")
    private ResponseEntity<TarifResponse> addTarif(@RequestBody @Valid TarifRequest tarifRequest){
        return new ResponseEntity<>(this.tarifService.addTarif(tarifRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all/{ecoleId}")
    private ResponseEntity<List<TarifResponse>> getTarif(@PathVariable(name = "ecoleId") Long ecoleId){
        return new ResponseEntity<>(this.tarifService.getAll(ecoleId), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    private ResponseEntity<TarifResponse> editTarif(@PathVariable(name = "id") Long id,@RequestBody @Valid  TarifRequest tarifRequest){
        return new ResponseEntity<>(this.tarifService.editTarif(id, tarifRequest), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/delete/{id}")
    private ResponseEntity<TarifResponse> deleteTarif(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(this.tarifService.deleteTarif(id), HttpStatus.ACCEPTED);
    }
}
