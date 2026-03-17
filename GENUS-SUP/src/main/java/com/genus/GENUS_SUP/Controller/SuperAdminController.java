package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.ISuperAdminService;
import com.genus.GENUS_SUP.dto.superAdmin_dto.SuperAdminRequest;
import com.genus.GENUS_SUP.dto.superAdmin_dto.SuperAdminResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/super")
public class SuperAdminController {
    private final ISuperAdminService superAdminService;

    @PostMapping("/add")
    public ResponseEntity<SuperAdminResponse> addSuperAdmin(@RequestBody @Valid SuperAdminRequest superAdmin) {
        return new ResponseEntity<>(this.superAdminService.addSuperAdmin(superAdmin), HttpStatus.CREATED);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SuperAdminResponse> getSuperAdmin(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(this.superAdminService.getSuperAdmin(id), HttpStatus.OK);
    }

    @GetMapping("/getAll/{ecoleId}")
    public ResponseEntity<List<SuperAdminResponse>> getAllSuperAdmin(@PathVariable(name = "ecoleId") Long ecoleId) {
        return new ResponseEntity<>(this.superAdminService.getSAdminByEcole(ecoleId), HttpStatus.OK);
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<SuperAdminResponse> editSuperAdmin(@PathVariable(name = "email") String email,@RequestBody SuperAdminRequest superAdmin) {
        return new ResponseEntity<>(this.superAdminService.editSuperAdmin(email,superAdmin), HttpStatus.ACCEPTED);
    }
}
