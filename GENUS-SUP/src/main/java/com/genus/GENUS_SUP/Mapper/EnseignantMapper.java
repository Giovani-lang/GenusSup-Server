package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Enseignant;
import com.genus.GENUS_SUP.dto.enseignant_dto.EnseignantRequestDTO;
import com.genus.GENUS_SUP.dto.enseignant_dto.EnseignantResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface EnseignantMapper {
    Enseignant fromEnseignantRequestDTO (EnseignantRequestDTO enseignantRequestDTO);

    @Mapping(source = "ecole", target = "ecole")
    @Mapping(source ="matieres", target = "matieres")
//    @Mapping(source = "department", target = "department")
    EnseignantResponseDTO fromEnseignant (Enseignant enseignant);
}
