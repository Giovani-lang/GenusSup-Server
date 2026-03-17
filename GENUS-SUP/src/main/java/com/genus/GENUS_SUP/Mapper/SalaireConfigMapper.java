package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.SalaireConfig;
import com.genus.GENUS_SUP.dto.salaireConfig_dto.SalaireConfigRequest;
import com.genus.GENUS_SUP.dto.salaireConfig_dto.SalaireConfigResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface SalaireConfigMapper {

    SalaireConfig fromSalaireConfigRequest (SalaireConfigRequest salaireConfigRequest);

    @Mapping(source = "ecole", target = "ecole")
    SalaireConfigResponse fromSalaireConfig (SalaireConfig salaireConfig);
}
