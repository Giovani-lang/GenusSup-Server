package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.Appartenance;
import com.logonedigital.PI.SCHULE.dto.appartenance_dto.AppartenanceRequest;
import com.logonedigital.PI.SCHULE.dto.appartenance_dto.AppartenanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration

public interface AppartenanceMapper {

    Appartenance fromAppartenanceRequest (AppartenanceRequest appartenanceRequest);


            @Mapping(source = "etudiant", target = "etudiant")
            @Mapping(source = "option", target = "option")
            @Mapping(source = "anneeAcademique", target = "anneeAcademique")
    AppartenanceResponse fromAppartenance (Appartenance appartenance);
}
