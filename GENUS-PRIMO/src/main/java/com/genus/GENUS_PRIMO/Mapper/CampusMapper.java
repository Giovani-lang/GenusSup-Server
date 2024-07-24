package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.Campus;
import com.genus.GENUS_PRIMO.Entity.Cycle;
import com.genus.GENUS_PRIMO.dto.campus_dto.CampusRequest;
import com.genus.GENUS_PRIMO.dto.campus_dto.CampusResponse;
import com.genus.GENUS_PRIMO.dto.cycle_dto.CycleRequest;
import com.genus.GENUS_PRIMO.dto.cycle_dto.CycleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface CampusMapper {

    Campus fromCampusRequest (CampusRequest campusRequest);

    @Mapping(source = "cycles", target = "cycles")
    CampusResponse fromCampus (Campus campus);
}
