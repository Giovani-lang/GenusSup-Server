package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Emargement;
import com.genus.GENUS_SUP.dto.emargement_dto.EmargementRequest;
import com.genus.GENUS_SUP.dto.emargement_dto.EmargementResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface EmargementMapper {

    Emargement fromEmargementRequest (EmargementRequest emargementRequest);

    @Mapping(target = "enseignant", source = "enseignant")
    @Mapping(target = "planification", source = "planification")
    EmargementResponse fromEmargement (Emargement emargement);
}
