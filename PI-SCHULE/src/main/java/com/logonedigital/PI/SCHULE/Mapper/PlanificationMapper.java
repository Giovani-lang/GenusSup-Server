package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.Planification;
import com.logonedigital.PI.SCHULE.dto.planification_dto.PlanificationRequest;
import com.logonedigital.PI.SCHULE.dto.planification_dto.PlanificationResponse;
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
