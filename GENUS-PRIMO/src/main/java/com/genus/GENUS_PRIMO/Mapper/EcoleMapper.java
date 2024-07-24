package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.Ecole;
import com.genus.GENUS_PRIMO.dto.ecole_dto.EcoleRequest;
import com.genus.GENUS_PRIMO.dto.ecole_dto.EcoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface EcoleMapper {

    Ecole fromEcoleRequest (EcoleRequest ecoleRequest);

    @Mapping(source = "campus", target = "campus")
    EcoleResponse fromEcole (Ecole ecole);
}
