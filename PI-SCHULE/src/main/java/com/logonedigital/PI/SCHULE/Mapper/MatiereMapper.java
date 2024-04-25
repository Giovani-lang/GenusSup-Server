package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.Matiere;
import com.logonedigital.PI.SCHULE.dto.matiere_dto.MatiereRequest;
import com.logonedigital.PI.SCHULE.dto.matiere_dto.MatiereResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface MatiereMapper {
    Matiere fromMatiereRequest (MatiereRequest matiereRequest);

    @Mapping(target = "option", source = "option")
    @Mapping(target = "enseignant", source = "enseignant")
    MatiereResponse fromMatiere (Matiere matiere);
}
