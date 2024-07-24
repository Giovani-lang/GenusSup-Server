package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.Tarif;
import com.genus.GENUS_PRIMO.dto.tarif_dto.TarifRequest;
import com.genus.GENUS_PRIMO.dto.tarif_dto.TarifResponse;
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
