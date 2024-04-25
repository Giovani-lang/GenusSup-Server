package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.Ecole;
import com.logonedigital.PI.SCHULE.dto.ecole_dto.EcoleRequest;
import com.logonedigital.PI.SCHULE.dto.ecole_dto.EcoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface EcoleMapper {

    Ecole fromEcoleRequest (EcoleRequest ecoleRequest);

    @Mapping(source = "cycles", target = "cycles")
    EcoleResponse fromEcole ( Ecole ecole);
}
