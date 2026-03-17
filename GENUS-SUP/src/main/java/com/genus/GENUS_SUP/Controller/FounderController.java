package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IFounderService;
import com.genus.GENUS_SUP.dto.founder_dto.FounderRequest;
import com.genus.GENUS_SUP.dto.founder_dto.FounderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/founders")
public class FounderController {
    private final IFounderService founderService;

    @PostMapping("/add")
    public ResponseEntity<FounderResponse> addFounder(@RequestBody @Valid FounderRequest founder) {
        return new ResponseEntity<>(this.founderService.addFounder(founder), HttpStatus.CREATED);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<FounderResponse> getFounder(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.founderService.getFounder(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FounderResponse>> getAllFounders() {
        return new ResponseEntity<>(this.founderService.getAllFounders(), HttpStatus.OK);
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<FounderResponse> editFounder(@PathVariable(name = "email") String email,@RequestBody FounderRequest founder) {
        return new ResponseEntity<>(this.founderService.editFounder(email,founder), HttpStatus.ACCEPTED);
    }
}
