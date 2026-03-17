package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Tranche;
import com.genus.GENUS_SUP.dto.tranche_dto.TrancheRequest;
import com.genus.GENUS_SUP.dto.tranche_dto.TrancheResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface TrancheMapper {
    Tranche fromTrancheRequest (TrancheRequest trancheRequest);

    @Mapping(source = "tarif", target = "tarif")
    TrancheResponse fromTranche (Tranche tranche);
}
