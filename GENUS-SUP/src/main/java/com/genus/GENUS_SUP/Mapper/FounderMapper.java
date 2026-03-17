package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Founder;
import com.genus.GENUS_SUP.dto.founder_dto.FounderRequest;
import com.genus.GENUS_SUP.dto.founder_dto.FounderResponse;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface FounderMapper {
    Founder fromFounderRequest (FounderRequest founderRequest);

    FounderResponse fromFounder (Founder founder);

}
