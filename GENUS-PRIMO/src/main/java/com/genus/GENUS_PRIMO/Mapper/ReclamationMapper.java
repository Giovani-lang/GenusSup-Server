package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.Reclamation;
import com.genus.GENUS_PRIMO.dto.reclamation_dto.ReclamationRequest;
import com.genus.GENUS_PRIMO.dto.reclamation_dto.ReclamationResponse;
import com.genus.GENUS_PRIMO.dto.reclamation_dto.ReclamationTreated;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface ReclamationMapper {

    Reclamation fromReclamationRequest (ReclamationRequest reclamationRequest);
    Reclamation fromReclamationTreated (ReclamationTreated reclamationTreated);
    ReclamationResponse fromReclamation (Reclamation reclamation);
}
