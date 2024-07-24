package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.etudiant_dto.EtudiantRequestDTO;
import com.genus.GENUS_PRIMO.dto.etudiant_dto.EtudiantResponseDTO;

import java.util.List;

public interface IEtudiantService {
    EtudiantResponseDTO addEtudiant(EtudiantRequestDTO etudiantRequestDTO);
    EtudiantResponseDTO getEtudiantByMatricule(String matricule);
//    EtudiantResponseDTO getEtudiantByEmail(String email);
    List<EtudiantResponseDTO> getEtudiants();
    List<EtudiantResponseDTO> getEtudiantsByEcole(Long ecoleId);
    EtudiantResponseDTO updateEtudiant(String matricule, EtudiantRequestDTO etudiantRequestDTO);
}
