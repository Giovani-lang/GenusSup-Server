package com.genus.GENUS_PRIMO.Controller;

import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Service.Interface.IAdminService;
import com.genus.GENUS_PRIMO.dto.admin_dto.AdminRequestDTO;
import com.genus.GENUS_PRIMO.dto.admin_dto.AdminResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/administrations")
public class AdminController {
    private final IAdminService IAdminService;
    public AdminController(IAdminService IAdminService){
        this.IAdminService = IAdminService;
    }
    @PostMapping("/add")
    public ResponseEntity<AdminResponseDTO> addAdministration(@RequestBody @Valid AdminRequestDTO administration) throws RessourceNotFoundException {
        return new ResponseEntity<>( this.IAdminService.addAdministration(administration), HttpStatus.CREATED);
}
    @GetMapping("/getAll")
    public ResponseEntity<List<AdminResponseDTO>> getAdministrations() {
        return new ResponseEntity<>(this.IAdminService.getAdministrations(),HttpStatus.OK);
    }

    @GetMapping("/getAllByEcole/{ecoleId}")
    public ResponseEntity<List<AdminResponseDTO>> getEtudiantsByEcole(@PathVariable(name = "ecoleId") Long ecoleId) {
        return new ResponseEntity<>(this.IAdminService.getAdministrationsByEcole(ecoleId), HttpStatus.OK);
    }

    @GetMapping("/detail/{email}")
    public ResponseEntity<AdminResponseDTO> getAdministration(@PathVariable(name = "email")String email) throws RessourceNotFoundException {
     return new ResponseEntity<>(this.IAdminService.getAdministration(email), HttpStatus.OK);
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<AdminResponseDTO> updateAdministration(@PathVariable(name = "email") String email,
                                                               @RequestBody  AdminRequestDTO administration) throws RessourceNotFoundException{
        return new ResponseEntity<>(this.IAdminService.updateAdministration(email,administration), HttpStatus.ACCEPTED);
}

}
