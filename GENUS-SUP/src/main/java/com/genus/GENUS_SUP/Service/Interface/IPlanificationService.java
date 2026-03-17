package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.planification_dto.PlanificationRequest;
import com.genus.GENUS_SUP.dto.planification_dto.PlanificationResponse;

import java.util.List;

public interface IPlanificationService {
    PlanificationResponse addPlanning(PlanificationRequest lemploi);
    List<PlanificationResponse> findAllPlanningByOption(Long ecoleId,Long optionId);
    List<PlanificationResponse> findAllPlanningForTeacher(Long ecoleId,String ensEmail);
    PlanificationResponse updatePlanning(Long id, PlanificationRequest lemploi);
    PlanificationResponse editStatus (Long id);
    PlanificationResponse cancelPlanning (Long id);
}
