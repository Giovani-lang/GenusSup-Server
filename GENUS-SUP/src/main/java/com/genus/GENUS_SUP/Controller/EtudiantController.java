package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Service.Interface.IEtudiantService;
import com.genus.GENUS_SUP.dto.etudiant_dto.EtudiantRequestDTO;
import com.genus.GENUS_SUP.dto.etudiant_dto.EtudiantResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final IEtudiantService etudiantService;

    @PostMapping("/add")
    public ResponseEntity<EtudiantResponseDTO> addEtudiant(@Valid @RequestBody EtudiantRequestDTO etudiantRequestDTO)  {
        return new ResponseEntity<>(this.etudiantService.addEtudiant(etudiantRequestDTO),HttpStatus.CREATED );
    }

    @GetMapping("/detailWithMatricule/{matricule}")
    public ResponseEntity<EtudiantResponseDTO> getEtudiantByMatricule(@PathVariable(name = "matricule") String matricule)
            throws RessourceNotFoundException {
        return new ResponseEntity<>(this.etudiantService.getEtudiantByMatricule(matricule),HttpStatus.OK );
    }

//    @GetMapping("/detailWithEmail/{email}")
//    public ResponseEntity<EtudiantResponseDTO> getEtudiantByEmail(@PathVariable(name = "email") String email)
//            throws RessourceNotFoundException{
//        return new ResponseEntity<>(this.etudiantService.getEtudiantByEmail(email),HttpStatus.ACCEPTED );
//    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EtudiantResponseDTO>> getEtudiants() {
        return new ResponseEntity<>(this.etudiantService.getEtudiants(), HttpStatus.OK);
    }

    @GetMapping("/getAllByEcole/{ecoleId}")
    public ResponseEntity<List<EtudiantResponseDTO>> getEtudiantsByEcole(@PathVariable(name = "ecoleId") Long ecoleId) {
        return new ResponseEntity<>(this.etudiantService.getEtudiantsByEcole(ecoleId), HttpStatus.OK);
    }

    @PutMapping("/edit/{matricule}")
    public ResponseEntity<EtudiantResponseDTO> updateEtudiant(@PathVariable(name = "matricule") String matricule,
                                                              @RequestBody  EtudiantRequestDTO etudiantRequestDTO)
                                                              throws RessourceNotFoundException {
        return new ResponseEntity<>(this.etudiantService.updateEtudiant(matricule, etudiantRequestDTO),HttpStatus.ACCEPTED );
    }

}
