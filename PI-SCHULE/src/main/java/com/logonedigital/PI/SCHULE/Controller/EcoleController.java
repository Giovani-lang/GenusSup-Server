package com.logonedigital.PI.SCHULE.Controller;

import com.logonedigital.PI.SCHULE.Service.Interface.IEcoleService;
import com.logonedigital.PI.SCHULE.dto.ecole_dto.EcoleRequest;
import com.logonedigital.PI.SCHULE.dto.ecole_dto.EcoleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/ecoles")
public class EcoleController {
    private final IEcoleService ecoleService;

    @PostMapping("/add")
    public ResponseEntity<EcoleResponse> addEcole(@Valid  @RequestBody EcoleRequest ecole) {
        return new ResponseEntity<>(this.ecoleService.addEcole(ecole), HttpStatus.CREATED);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<EcoleResponse> getEcole(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.ecoleService.getEcole(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EcoleResponse>> getAllEcole() {
        return new ResponseEntity<>(this.ecoleService.getAllEcole(), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EcoleResponse> editEcole(@PathVariable(name = "id") Long id,
                                                   @RequestBody @Valid  EcoleRequest ecole) {
        return new ResponseEntity<>(this.ecoleService.editEcole(id,ecole), HttpStatus.ACCEPTED);
    }
}
