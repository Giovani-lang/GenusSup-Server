package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.Filiere;
import com.logonedigital.PI.SCHULE.dto.filiere_dto.FiliereRequest;
import com.logonedigital.PI.SCHULE.dto.filiere_dto.FiliereResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface FiliereMapper {

    Filiere fromFiliereRequest (FiliereRequest filiereRequest);

    @Mapping(source = "classes", target = "classes")
    FiliereResponse fromFiliere (Filiere filiere);
}
