package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.AnneeAcademique;
import com.genus.GENUS_SUP.dto.anneeAcademique_dto.AnneeAcademiqueRequest;
import com.genus.GENUS_SUP.dto.anneeAcademique_dto.AnneeAcademiqueResponse;
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
