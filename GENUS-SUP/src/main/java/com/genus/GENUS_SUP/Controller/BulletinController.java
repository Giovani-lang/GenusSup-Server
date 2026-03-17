package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IBulletinService;
import com.genus.GENUS_SUP.dto.bulletin_dto.BulletinRequest;
import com.genus.GENUS_SUP.dto.bulletin_dto.BulletinResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bulletins")
public class BulletinController {
    private final IBulletinService bulletinService;


    @PostMapping("/add")
    public ResponseEntity<BulletinResponse> addBulletin(@RequestBody @Valid BulletinRequest bulletinRequest){
        return new ResponseEntity<>(this.bulletinService.addBulletin(bulletinRequest), HttpStatus.CREATED);
    }

    @GetMapping("/detail/{enseignantId}")
    public ResponseEntity<List<BulletinResponse>> findBulletinByEnseignant(
                @PathVariable(name = "enseignantId")Long enseignantId
    ){
        return new ResponseEntity<>(this.bulletinService.getBulletin(enseignantId), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BulletinResponse>findBulletinById(@PathVariable(name="id")Long id){
        return new ResponseEntity<>(this.bulletinService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/getAllByEcole/{ecoleId}")
    public ResponseEntity<List<BulletinResponse>>getAllBulletin(@PathVariable(name="ecoleId")Long ecoleId){
        return new ResponseEntity<>(this.bulletinService.getAllBulletin(ecoleId), HttpStatus.OK);
    }

}
