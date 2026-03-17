package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.ITrancheService;
import com.genus.GENUS_SUP.dto.tranche_dto.TrancheRequest;
import com.genus.GENUS_SUP.dto.tranche_dto.TrancheResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tranches")
@RequiredArgsConstructor
public class TrancheController {
    private final ITrancheService trancheService;

    @PostMapping("/add")
    ResponseEntity<TrancheResponse> addTranche(@Valid @RequestBody TrancheRequest trancheRequest){
        return new ResponseEntity<>(this.trancheService.addTranche(trancheRequest), HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    ResponseEntity<TrancheResponse> editTranche(@PathVariable(name = "id") Long id,@RequestBody TrancheRequest trancheRequest){
        return new ResponseEntity<>(this.trancheService.editTranche(id,trancheRequest), HttpStatus.ACCEPTED);
    }
    @GetMapping("/search/{tarifId}")
    ResponseEntity<List<TrancheResponse>> search(@PathVariable(name = "tarifId") Long taridId){
        return new ResponseEntity<>(this.trancheService.search(taridId), HttpStatus.OK);
    }
}
