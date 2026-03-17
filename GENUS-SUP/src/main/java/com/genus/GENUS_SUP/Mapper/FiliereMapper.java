package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Departement;
import com.genus.GENUS_SUP.Entity.Filiere;
import com.genus.GENUS_SUP.dto.departement_dto.DepartementRequest;
import com.genus.GENUS_SUP.dto.departement_dto.DepartementResponse;
import com.genus.GENUS_SUP.dto.filiere_dto.FiliereRequest;
import com.genus.GENUS_SUP.dto.filiere_dto.FiliereResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface FiliereMapper {

    Filiere fromFiliereRequest (FiliereRequest filiereRequest);

    @Mapping(source = "classes", target = "classes")
    FiliereResponse fromFiliere (Filiere filiere);
}
