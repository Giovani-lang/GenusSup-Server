package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Option;
import com.genus.GENUS_SUP.dto.option_dto.OptionRequest;
import com.genus.GENUS_SUP.dto.option_dto.OptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface OptionMapper {
    Option fromOptionRequest(OptionRequest optionRequest);

    @Mapping(source = "classe", target = "classe")
    @Mapping(source = "tarif", target = "tarif")
    OptionResponse fromOption (Option option);
}
