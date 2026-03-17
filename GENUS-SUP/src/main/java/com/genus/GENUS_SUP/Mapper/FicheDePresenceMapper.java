package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.dto.ficheDePresence_dto.FicheDePresenceRequest;
import com.genus.GENUS_SUP.dto.ficheDePresence_dto.FicheDePresenceResponse;
import com.genus.GENUS_SUP.Entity.FicheDePresence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper(componentModel = "spring")
@Configuration
public interface FicheDePresenceMapper {

    FicheDePresence fromFicheDePresenceResquest (FicheDePresenceRequest ficheDePresenceRequest);
    @Mapping(target = "etudiant", source = "etudiant")
    @Mapping(target = "planification", source = "planification")
    FicheDePresenceResponse toFicheDePresence (FicheDePresence ficheDePresence); // Just for justify absence
    @Mapping(target = "etudiant", source = "etudiant")
    @Mapping(target = "planification", source = "planification")
    List<FicheDePresenceResponse> fromFicheDePresence (List<FicheDePresence> ficheDePresence);
}
