package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Tarif;
import com.genus.GENUS_SUP.dto.tarif_dto.TarifRequest;
import com.genus.GENUS_SUP.dto.tarif_dto.TarifResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface TarifMapper {
    Tarif fromTarifRequest (TarifRequest tarifRequest);

    @Mapping(source = "option", target = "option")
    @Mapping(source = "tranches", target = "tranches")
    TarifResponse fromTarif (Tarif tarif);

}
