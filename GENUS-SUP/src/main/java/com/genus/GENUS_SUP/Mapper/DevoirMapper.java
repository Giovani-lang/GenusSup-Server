package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Devoir;
import com.genus.GENUS_SUP.dto.devoir_dto.DevoirRequest;
import com.genus.GENUS_SUP.dto.devoir_dto.DevoirResponse;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface DevoirMapper {
    Devoir fromDevoirRequest (DevoirRequest devoirRequest);

    DevoirResponse fromDevoir (Devoir devoir);
}
