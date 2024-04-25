package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.*;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.EtudiantMapper;
import com.logonedigital.PI.SCHULE.Repository.*;
import com.logonedigital.PI.SCHULE.Service.Interface.IEtudiantService;
import com.logonedigital.PI.SCHULE.dto.etudiant_dto.EtudiantRequestDTO;
import com.logonedigital.PI.SCHULE.dto.etudiant_dto.EtudiantResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepo;
    private final EtudiantMapper etudiantMapper;
    private final PasswordEncoder encoder;
    private final EcoleRepository ecoleRepo;


    public String generateUniqueMatricule() {
        String matricule;
        do {
            matricule = generateMatricule();
        } while (etudiantRepo.findByMatricule(matricule).isPresent());
        return matricule;
    }

    private String generateMatricule() {
        final int PREFIX_LENGTH = 4;
        final int SUFFIX_LENGTH = 3;

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder prefixBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < PREFIX_LENGTH; i++) {
            prefixBuilder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        String suffix = String.format("%0" + SUFFIX_LENGTH + "d", random.nextInt(1000));

        return prefixBuilder.toString() + suffix;
    }

    @Override
    public EtudiantResponseDTO addEtudiant(EtudiantRequestDTO etudiantRequestDTO) throws RessourceExistException {
        Optional<Etudiant> etu1 = this.etudiantRepo.findByEmail(etudiantRequestDTO.getEmail());
        Optional<Etudiant> etu2 = this.etudiantRepo.findByTelephone(etudiantRequestDTO.getTelephone());
        Etudiant etu = this.etudiantMapper.fromEtudiantRequestDTO(etudiantRequestDTO);
        if (etu1.isPresent()){
            throw new RessourceExistException("Student with this email already exist !!!");
        } else if (etu2.isPresent()) {
            throw new RessourceExistException("Student with this phone already exist !!!");
        }
        Ecole ecole = this.ecoleRepo.findById(etudiantRequestDTO.getEcoleId())
                .orElseThrow(()-> new RessourceNotFoundException("School"+etudiantRequestDTO.getEcoleId()+"doesn't exsit" ));
        etu.setEcole(ecole);
        etu.setMatricule(generateUniqueMatricule());
        etu.setCreatedAt(new Date());
        etu.setStatus("Actif");
        etu.setRole("ETUDIANT");
        etu.setPassword(this.encoder.encode(etu.getPassword()));
        return this.etudiantMapper.fromEtudiant(this.etudiantRepo.save(etu));
    }

    @Override
    public EtudiantResponseDTO getEtudiantByMatricule(String matricule) throws RessourceNotFoundException {
        try {
            return this.etudiantMapper.fromEtudiant(this.etudiantRepo.findByMatricule(matricule).get());
        }catch (Exception ex){
            throw new RessourceNotFoundException("This matricule " +matricule+ " doesn't exist in our data base");
        }
    }

    @Override
    public EtudiantResponseDTO getEtudiantByEmail(String email) throws RessourceNotFoundException {
        try {
            return this.etudiantMapper.fromEtudiant(this.etudiantRepo.findByEmail(email).get());
        }catch (Exception ex){
            throw new RessourceNotFoundException("This matricule " +email+ " doesn't exist in our data base");
        }
    }

    @Override
    public List<EtudiantResponseDTO> getEtudiants() {
        List<Etudiant> etudiants = this.etudiantRepo.findAll();
        List<EtudiantResponseDTO> etudiantResponses = new ArrayList<>();
        etudiants.forEach(etudiant -> etudiantResponses.add(this.etudiantMapper.fromEtudiant(etudiant)));
        return etudiantResponses;
    }

    @Override
    public List<EtudiantResponseDTO> getEtudiantsByEcole(Long ecoleId) {
        List<Etudiant> etudiants = this.etudiantRepo.findAllByEcole(ecoleId);
        List<EtudiantResponseDTO> etudiantResponses = new ArrayList<>();
        etudiants.forEach(etudiant -> etudiantResponses.add(this.etudiantMapper.fromEtudiant(etudiant)));
        return etudiantResponses;
    }

    @Override
    public EtudiantResponseDTO updateEtudiant(String matricule, EtudiantRequestDTO etudiantRequestDTO)throws RessourceNotFoundException {
try {
    Etudiant etu = this.etudiantRepo.findByMatricule(matricule)
            .orElseThrow(() -> new RessourceNotFoundException("This matricule " + matricule + " doesn't exist in our data base"));

    Etudiant etudiant = this.etudiantMapper.fromEtudiantRequestDTO(etudiantRequestDTO);
    etu.setEmail(etudiant.getEmail());
    etu.setImage_url(etudiant.getImage_url());
    etu.setNom(etudiant.getNom());
    etu.setPrenom(etudiant.getPrenom());
    etu.setTelephone(etudiant.getTelephone());

    if (etudiant.getPassword().isEmpty()) {
        etu.setPassword(etu.getPassword());
    } else etu.setPassword(this.encoder.encode(etudiant.getPassword()));

    etu.setDateNaissance(etudiant.getDateNaissance());
    etu.setGenre(etudiant.getGenre());
    etu.setDateInscription(etudiant.getDateInscription());
    etu.setStatus(etudiant.getStatus());
    etu.setUpdatedAt(new Date());
    return this.etudiantMapper.fromEtudiant(this.etudiantRepo.save(etu));
}catch (NoSuchElementException ex){
    throw new RessourceNotFoundException("This student doesn't exist");
}
    }

}
