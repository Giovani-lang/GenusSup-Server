package com.logonedigital.PI.SCHULE.Controller;

import com.logonedigital.PI.SCHULE.Service.Interface.ICycleService;
import com.logonedigital.PI.SCHULE.dto.cycle_dto.CycleRequest;
import com.logonedigital.PI.SCHULE.dto.cycle_dto.CycleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cycles")
@RequiredArgsConstructor
public class CycleController {
    private final ICycleService cycleService;

    @PostMapping("/add")
    public ResponseEntity<CycleResponse> addOption(@RequestBody @Valid CycleRequest cycleRequest){
        return new ResponseEntity<>(this.cycleService.addCycle(cycleRequest), HttpStatus.CREATED);
    }
    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<CycleResponse>> getAllOption(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(this.cycleService.getCycles(id), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<CycleResponse> updateCycle(@PathVariable(name = "id")Long id,
                                                     @RequestBody @Valid  CycleRequest cycleRequest){
        return new ResponseEntity<>(this.cycleService.editCycle(id, cycleRequest), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/delete/{id}")
    public ResponseEntity<CycleResponse> deleteCycle(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.cycleService.deleteCycle(id), HttpStatus.ACCEPTED);
    }
}
