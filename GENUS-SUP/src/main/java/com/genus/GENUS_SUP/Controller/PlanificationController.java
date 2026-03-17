package com.genus.GENUS_SUP.Controller;

import com.genus.GENUS_SUP.Service.Interface.IPlanificationService;
import com.genus.GENUS_SUP.dto.planification_dto.PlanificationRequest;
import com.genus.GENUS_SUP.dto.planification_dto.PlanificationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planifications")
@RequiredArgsConstructor
public class PlanificationController {
    private final IPlanificationService planificationService;

    @PostMapping("/add")
    public ResponseEntity<PlanificationResponse> addPlanning(@RequestBody @Valid PlanificationRequest lemploi){
        return new ResponseEntity<>(this.planificationService.addPlanning(lemploi), HttpStatus.CREATED);
    }

    @GetMapping("/getAllByOption/{ecoleId}/{optionId}")
    public ResponseEntity<List<PlanificationResponse>> findAllPlanningByOption(
            @PathVariable(name = "ecoleId") Long ecoleId,
            @PathVariable(name = "optionId") Long optionId){
        return new ResponseEntity<>(this.planificationService.findAllPlanningByOption(ecoleId,optionId), HttpStatus.OK);
    }
    @GetMapping("/getAllForTeacher/{ecoleId}/{ensEmail}")
    public ResponseEntity<List<PlanificationResponse>> findAllPlanningForTeacher(
            @PathVariable(name = "ecoleId") Long ecoleId,
            @PathVariable(name = "ensEmail") String ensEmail){
        return new ResponseEntity<>(this.planificationService.findAllPlanningForTeacher(ecoleId,ensEmail), HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<PlanificationResponse> updatePlanning(@PathVariable(name = "id")Long id, @RequestBody @Valid  PlanificationRequest lemploi){
        return new ResponseEntity<>(this.planificationService.updatePlanning(id, lemploi),HttpStatus.ACCEPTED);
    }
    @PatchMapping("/completed/{id}")
    public ResponseEntity<PlanificationResponse> completedPlanning(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.planificationService.editStatus(id), HttpStatus.ACCEPTED);
    }
    @PatchMapping("/cancel/{id}")
    public ResponseEntity<PlanificationResponse> cancelPlanning(@PathVariable(name = "id")Long id){
        return new ResponseEntity<>(this.planificationService.cancelPlanning(id), HttpStatus.ACCEPTED);
    }
}
