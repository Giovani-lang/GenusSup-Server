package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Matiere;
import com.genus.GENUS_SUP.dto.matiere_dto.MatiereRequest;
import com.genus.GENUS_SUP.dto.matiere_dto.MatiereResponse;
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
