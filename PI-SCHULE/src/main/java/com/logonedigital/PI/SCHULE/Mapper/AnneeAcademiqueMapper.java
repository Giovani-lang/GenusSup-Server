package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.AnneeAcademique;
import com.logonedigital.PI.SCHULE.dto.anneeAcademique_dto.AnneeAcademiqueRequest;
import com.logonedigital.PI.SCHULE.dto.anneeAcademique_dto.AnneeAcademiqueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface AnneeAcademiqueMapper {

    AnneeAcademique fromAnneeAcademiqueRequest (AnneeAcademiqueRequest anneeAcademiqueRequest);

    @Mapping(source = "ecole", target = "ecole")
    AnneeAcademiqueResponse fromAnneeAcademique (AnneeAcademique anneeAcademique);
}
