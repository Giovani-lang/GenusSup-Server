package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.*;
import com.logonedigital.PI.SCHULE.dto.etudiant_dto.EtudiantRequestDTO;
import com.logonedigital.PI.SCHULE.dto.etudiant_dto.EtudiantResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface EtudiantMapper {
    Etudiant fromEtudiantRequestDTO (EtudiantRequestDTO etudiantRequestDTO);

    @Mapping(source = "ecole", target = "ecole")
    EtudiantResponseDTO fromEtudiant (Etudiant etudiant);
}
