package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.Reclamation;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationRequest;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationResponse;
import com.logonedigital.PI.SCHULE.dto.reclamation_dto.ReclamationTreated;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface ReclamationMapper {

    Reclamation fromReclamationRequest (ReclamationRequest reclamationRequest);
    Reclamation fromReclamationTreated (ReclamationTreated reclamationTreated);
    ReclamationResponse fromReclamation (Reclamation reclamation);
}
