package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.Tarif;
import com.logonedigital.PI.SCHULE.dto.tarif_dto.TarifRequest;
import com.logonedigital.PI.SCHULE.dto.tarif_dto.TarifResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface TarifMapper {
    Tarif fromTarifRequest (TarifRequest tarifRequest);

    @Mapping(source = "option", target = "option")
    TarifResponse fromTarif (Tarif tarif);

}
