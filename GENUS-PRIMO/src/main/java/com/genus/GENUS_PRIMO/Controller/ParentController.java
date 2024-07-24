package com.genus.GENUS_PRIMO.Controller;

import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Service.Interface.IParentService;
import com.genus.GENUS_PRIMO.dto.parent_dto.ParentRequest;
import com.genus.GENUS_PRIMO.dto.parent_dto.ParentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parents")
@RequiredArgsConstructor
public class ParentController {
    private final IParentService parentService;

    @PostMapping("/add")
    public ResponseEntity<ParentResponse> addTuteur(@RequestBody @Valid ParentRequest parentRequest) throws RessourceNotFoundException {
        return new ResponseEntity<>( this.parentService.addParent(parentRequest), HttpStatus.CREATED);
    }
    @GetMapping("/getAllByEcole/{ecoleId}")
    public ResponseEntity<List<ParentResponse>> getEtudiantsByEcole(@PathVariable(name = "ecoleId") Long ecoleId) {
        return new ResponseEntity<>(this.parentService.search(ecoleId), HttpStatus.OK);
    }

    @GetMapping("/detail/{email}")
    public ResponseEntity<ParentResponse> getAdministration(@PathVariable(name = "email")String email) throws RessourceNotFoundException {
     return new ResponseEntity<>(this.parentService.getParent(email), HttpStatus.OK);
    }

    @PutMapping("/edit/{email}")
    public ResponseEntity<ParentResponse> editTuteur(@PathVariable(name = "email") String email,
                                                     @RequestBody ParentRequest parentRequest) throws RessourceNotFoundException{
        return new ResponseEntity<>(this.parentService.editParent(email, parentRequest), HttpStatus.ACCEPTED);
}

}
