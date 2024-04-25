package com.logonedigital.PI.SCHULE.Controller;

import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Service.Interface.IEnseignantService;
import com.logonedigital.PI.SCHULE.dto.admin_dto.AdminResponseDTO;
import com.logonedigital.PI.SCHULE.dto.enseignant_dto.EnseignantRequestDTO;
import com.logonedigital.PI.SCHULE.dto.enseignant_dto.EnseignantResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enseignants")
public class EnseignantController {
    private final IEnseignantService enseignantService;

    public EnseignantController(IEnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @PostMapping("/add")
    public ResponseEntity<EnseignantResponseDTO> addEnseignant(@RequestBody @Valid EnseignantRequestDTO enseignantRequestDTO){
        return new ResponseEntity<>(this.enseignantService.addEnseignant(enseignantRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/detail/{email}")
    public ResponseEntity<EnseignantResponseDTO> getEnseignant(@PathVariable(name = "email") String email) throws RessourceNotFoundException{
        return new ResponseEntity<>(this.enseignantService.getEnseignant(email),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EnseignantResponseDTO>> getEnseigants(){
        return new ResponseEntity<>(this.enseignantService.getEnseignants(),HttpStatus.OK);
    }

    @GetMapping("/getAllByEcole/{ecoleId}")
    public ResponseEntity<List<EnseignantResponseDTO>> getEtudiantsByEcole(@PathVariable(name = "ecoleId") Long ecoleId) {
        return new ResponseEntity<>(this.enseignantService.getEnseignantsByEcole(ecoleId), HttpStatus.OK);
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<EnseignantResponseDTO> updateEnseignant(@PathVariable(name = "email") String email,
                                                       @RequestBody  EnseignantRequestDTO enseignantRequestDTO) throws RessourceNotFoundException{
        return new ResponseEntity<>(this.enseignantService.updateEnseignant(email, enseignantRequestDTO),HttpStatus.ACCEPTED);
    }

}
