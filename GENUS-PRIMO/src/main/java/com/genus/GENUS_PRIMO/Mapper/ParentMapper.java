package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.Parent;
import com.genus.GENUS_PRIMO.dto.parent_dto.ParentRequest;
import com.genus.GENUS_PRIMO.dto.parent_dto.ParentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface ParentMapper {

    ParentMapper INSTANCE = Mappers.getMapper(ParentMapper.class);
    Parent fromTuteurRequest(ParentRequest parentRequest);

    @Mapping(source = "etudiant", target = "etudiant")
    ParentResponse fromTuteur(Parent parent);
}
