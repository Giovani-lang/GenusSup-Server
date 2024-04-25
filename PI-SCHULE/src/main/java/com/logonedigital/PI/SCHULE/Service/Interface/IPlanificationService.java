package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.dto.planification_dto.PlanificationRequest;
import com.logonedigital.PI.SCHULE.dto.planification_dto.PlanificationResponse;

import java.util.List;

public interface IPlanificationService {
    PlanificationResponse addPlanning(PlanificationRequest lemploi);
    List<PlanificationResponse> findAllPlanning();
    List<PlanificationResponse> findAllPlanningByOption(Long ecoleId,Long optionId);
    List<PlanificationResponse> findAllPlanningForTeacher(Long ecoleId,String ensEmail);
    PlanificationResponse updatePlanning(Long id, PlanificationRequest lemploi);
    void deletePlanning (Long id);
}
