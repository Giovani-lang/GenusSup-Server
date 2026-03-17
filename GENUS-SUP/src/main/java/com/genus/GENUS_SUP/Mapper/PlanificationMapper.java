package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Planification;
import com.genus.GENUS_SUP.dto.planification_dto.PlanificationRequest;
import com.genus.GENUS_SUP.dto.planification_dto.PlanificationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface PlanificationMapper {
    Planification fromPlanificationRequest (PlanificationRequest planificationRequest);

    @Mapping(source = "matiere", target = "matiere")
    @Mapping(source = "anneeAcademique", target = "anneeAcademique")
    PlanificationResponse fromPlanification (Planification planification);
}
