package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.Planification;
import com.genus.GENUS_PRIMO.dto.planification_dto.PlanificationRequest;
import com.genus.GENUS_PRIMO.dto.planification_dto.PlanificationResponse;
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
