package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.Cycle;
import com.genus.GENUS_PRIMO.dto.cycle_dto.CycleRequest;
import com.genus.GENUS_PRIMO.dto.cycle_dto.CycleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface CycleMapper {

    Cycle fromCycleRequest (CycleRequest cycleRequest);

    @Mapping(source = "classes", target = "classes")
    CycleResponse fromCycle (Cycle cycle);
}
