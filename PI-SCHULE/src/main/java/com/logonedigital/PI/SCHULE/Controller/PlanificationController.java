package com.logonedigital.PI.SCHULE.Controller;

import com.logonedigital.PI.SCHULE.Service.Interface.IPlanificationService;
import com.logonedigital.PI.SCHULE.dto.planification_dto.PlanificationRequest;
import com.logonedigital.PI.SCHULE.dto.planification_dto.PlanificationResponse;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<PlanificationResponse>> findAllPlanning(){
          return new ResponseEntity<>(this.planificationService.findAllPlanning(), HttpStatus.OK);
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
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePlanning(@PathVariable(name = "id")Long id){
        this.planificationService.deletePlanning(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
