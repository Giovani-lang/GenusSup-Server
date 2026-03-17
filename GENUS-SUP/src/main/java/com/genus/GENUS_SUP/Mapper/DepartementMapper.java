package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Departement;
import com.genus.GENUS_SUP.dto.departement_dto.DepartementRequest;
import com.genus.GENUS_SUP.dto.departement_dto.DepartementResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface DepartementMapper {

    Departement fromDepartemnetRequest (DepartementRequest departementRequest);

    @Mapping(source = "cycles", target = "cycles")
    @Mapping(source = "chief", target = "chief")
    DepartementResponse fromDepartement (Departement departement);
}
