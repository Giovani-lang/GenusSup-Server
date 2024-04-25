package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.Cycle;
import com.logonedigital.PI.SCHULE.dto.cycle_dto.CycleRequest;
import com.logonedigital.PI.SCHULE.dto.cycle_dto.CycleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface CycleMapper {

    Cycle fromCycleRequest (CycleRequest cycleRequest);

    @Mapping(source = "filieres", target = "filieres")
    CycleResponse fromCycle (Cycle cycle);
}
