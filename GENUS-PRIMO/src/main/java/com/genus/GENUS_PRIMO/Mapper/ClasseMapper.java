package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.Classe;
import com.genus.GENUS_PRIMO.dto.classe_dto.ClasseRequest;
import com.genus.GENUS_PRIMO.dto.classe_dto.ClasseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface ClasseMapper {

    Classe fromClasseRequest (ClasseRequest classeRequest);

    @Mapping(source = "options", target = "options")
    @Mapping(source = "cycle", target = "cycle")
    ClasseResponse fromClasse (Classe classe);
}
