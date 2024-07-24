package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.Ecole;
import com.genus.GENUS_PRIMO.Entity.Etudiant;
import com.genus.GENUS_PRIMO.Mapper.EtudiantMapper;
import com.genus.GENUS_PRIMO.Repository.EcoleRepository;
import com.genus.GENUS_PRIMO.Repository.EtudiantRepository;
import com.genus.GENUS_PRIMO.dto.etudiant_dto.EtudiantRequestDTO;
import com.genus.GENUS_PRIMO.dto.etudiant_dto.EtudiantResponseDTO;
import com.genus.GENUS_PRIMO.Exception.RessourceExistException;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Service.Interface.IEtudiantService;
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
//        Optional<Etudiant> etuEmail = this.etudiantRepo.findByEmail(etudiantRequestDTO.getEmail());
//        Optional<Etudiant> etuPhone = this.etudiantRepo.findByTelephone(etudiantRequestDTO.getTelephone());
        Etudiant etu = this.etudiantMapper.fromEtudiantRequestDTO(etudiantRequestDTO);
//        if (etuEmail.isPresent()){
//            throw new RessourceExistException("Cet email existe déjà");
//        } else if (etuPhone.isPresent()) {
//            throw new RessourceExistException("Ce numéro existe déjà");
//        }
        Ecole ecole = this.ecoleRepo.findById(etudiantRequestDTO.getEcoleId())
                .orElseThrow(()-> new RessourceNotFoundException("School"+etudiantRequestDTO.getEcoleId()+"doesn't exsit" ));
        etu.setEcole(ecole);
        etu.setMatricule(generateUniqueMatricule());
        etu.setCreatedAt(new Date());
        etu.setUsername(etu.getMatricule());
        etu.setStatus("Actif");
        etu.setRole("ELEVE");
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

//    @Override
//    public EtudiantResponseDTO getEtudiantByEmail(String email) throws RessourceNotFoundException {
//        try {
//            return this.etudiantMapper.fromEtudiant(this.etudiantRepo.findByEmail(email).get());
//        }catch (Exception ex){
//            throw new RessourceNotFoundException("This matricule " +email+ " doesn't exist in our data base");
//        }
//    }

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
//    etu.setEmail(etudiant.getEmail());
    etu.setImage_url(etudiant.getImage_url());
    etu.setNom(etudiant.getNom());
    etu.setPrenom(etudiant.getPrenom());
//    etu.setTelephone(etudiant.getTelephone());

    if (etudiant.getPassword().isEmpty()) {
        etu.setPassword(etu.getPassword());
    } else etu.setPassword(this.encoder.encode(etudiant.getPassword()));

    etu.setUsername(etu.getMatricule());
    etu.setDateNaissance(etudiant.getDateNaissance());
    etu.setGenre(etudiant.getGenre());
    etu.setStatus(etudiant.getStatus());
    etu.setUpdatedAt(new Date());
    return this.etudiantMapper.fromEtudiant(this.etudiantRepo.save(etu));
}catch (NoSuchElementException ex){
    throw new RessourceNotFoundException("This student doesn't exist");
}
    }

}
