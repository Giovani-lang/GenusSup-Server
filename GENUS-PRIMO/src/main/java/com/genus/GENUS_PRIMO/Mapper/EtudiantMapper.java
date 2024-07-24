package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.Etudiant;
import com.genus.GENUS_PRIMO.dto.etudiant_dto.EtudiantRequestDTO;
import com.genus.GENUS_PRIMO.dto.etudiant_dto.EtudiantResponseDTO;
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
