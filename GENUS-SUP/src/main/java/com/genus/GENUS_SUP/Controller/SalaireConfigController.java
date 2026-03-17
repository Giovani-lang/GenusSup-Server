package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.SalaireConfigService;
import com.genus.GENUS_SUP.dto.salaireConfig_dto.SalaireConfigRequest;
import com.genus.GENUS_SUP.dto.salaireConfig_dto.SalaireConfigResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/salaire-config")
@RequiredArgsConstructor
public class SalaireConfigController {
    private final SalaireConfigService salaireConfigService;

    @PostMapping("/add")
    public ResponseEntity<SalaireConfigResponse> createPayDay(@RequestBody SalaireConfigRequest salaireConfig){
        return new ResponseEntity<>(this.salaireConfigService.addPayDay(salaireConfig), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<SalaireConfigResponse> updatePayDay(@PathVariable(name = "id") Long id,@RequestBody SalaireConfigRequest salaireConfig){
        return new ResponseEntity<>(this.salaireConfigService.updatePayDay(id,salaireConfig), HttpStatus.ACCEPTED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<SalaireConfigResponse> getPayDay(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(this.salaireConfigService.getPayDay(id), HttpStatus.OK);
    }

}
