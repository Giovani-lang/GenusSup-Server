package com.genus.GENUS_PRIMO.Controller;

import com.genus.GENUS_PRIMO.Service.Interface.ICampusService;
import com.genus.GENUS_PRIMO.Service.Interface.ICycleService;
import com.genus.GENUS_PRIMO.dto.campus_dto.CampusRequest;
import com.genus.GENUS_PRIMO.dto.campus_dto.CampusResponse;
import com.genus.GENUS_PRIMO.dto.cycle_dto.CycleRequest;
import com.genus.GENUS_PRIMO.dto.cycle_dto.CycleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/campus")
@RequiredArgsConstructor
public class CampusController {
    private final ICampusService campusService;

    @PostMapping("/add")
    public ResponseEntity<CampusResponse> addCampus(@RequestBody @Valid CampusRequest campusRequest){
        return new ResponseEntity<>(this.campusService.addCampus(campusRequest), HttpStatus.CREATED);
    }
    @GetMapping("/getAll/{id}")
    public ResponseEntity<List<CampusResponse>> getAllCampus(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(this.campusService.getCampus(id), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<CampusResponse> updateCampus(@PathVariable(name = "id")Long id,
                                                     @RequestBody @Valid  CampusRequest campusRequest){
        return new ResponseEntity<>(this.campusService.editCampus(id, campusRequest), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/delete/{id}")
    public ResponseEntity<CampusResponse> deleteCampus(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.campusService.deleteCampus(id), HttpStatus.ACCEPTED);
    }
}
