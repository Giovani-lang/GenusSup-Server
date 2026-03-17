package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Appartenance;
import com.genus.GENUS_SUP.dto.appartenance_dto.AppartenanceRequest;
import com.genus.GENUS_SUP.dto.appartenance_dto.AppartenanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
