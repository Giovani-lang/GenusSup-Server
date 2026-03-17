package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Cycle;
import com.genus.GENUS_SUP.dto.cycle_dto.CycleRequest;
import com.genus.GENUS_SUP.dto.cycle_dto.CycleResponse;
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
