package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Etudiant;
import com.genus.GENUS_SUP.dto.etudiant_dto.EtudiantRequestDTO;
import com.genus.GENUS_SUP.dto.etudiant_dto.EtudiantResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface EtudiantMapper {
    Etudiant fromEtudiantRequestDTO (EtudiantRequestDTO etudiantRequestDTO);

    @Mapping(source = "ecole", target = "ecole")
    @Mapping(source = "dossiers", target = "dossiers")
    EtudiantResponseDTO fromEtudiant (Etudiant etudiant);
}
