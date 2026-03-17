package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Campus;
import com.genus.GENUS_SUP.dto.campus_dto.CampusRequest;
import com.genus.GENUS_SUP.dto.campus_dto.CampusResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface CampusMapper {

    Campus fromCampusRequest (CampusRequest campusRequest);

    @Mapping(source = "departements", target = "departements")
    @Mapping(source = "responsible", target = "responsible")
    CampusResponse fromCampus (Campus campus);
}
