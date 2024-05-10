package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.*;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.EnseignantMapper;
import com.logonedigital.PI.SCHULE.Repository.EcoleRepository;
import com.logonedigital.PI.SCHULE.Repository.EnseignantRepository;
import com.logonedigital.PI.SCHULE.Repository.FiliereRepository;
import com.logonedigital.PI.SCHULE.Repository.MatiereRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IEnseignantService;
import com.logonedigital.PI.SCHULE.dto.admin_dto.AdminResponseDTO;
import com.logonedigital.PI.SCHULE.dto.enseignant_dto.EnseignantRequestDTO;
import com.logonedigital.PI.SCHULE.dto.enseignant_dto.EnseignantResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnseignantServiceImpl implements IEnseignantService {

    private final EnseignantRepository enseignantRepo;
    private final EnseignantMapper enseignantMapper;
    private final PasswordEncoder encoder;
    private final EcoleRepository ecoleRepo;
    private final MatiereRepository matiereRepo;
    private final FiliereRepository filiereRepo;


    @Override
    public EnseignantResponseDTO addEnseignant(EnseignantRequestDTO enseignantRequestDTO) throws RessourceExistException {

        Enseignant ens = this.enseignantMapper.fromEnseignantRequestDTO(enseignantRequestDTO);
        Optional<Enseignant> ensEmail = this.enseignantRepo.findByEmail(enseignantRequestDTO.getEmail());
        Optional<Enseignant> ensPhone = this.enseignantRepo.findByTelephone(enseignantRequestDTO.getTelephone());
        if (ensEmail.isPresent()){
            throw new RessourceExistException("Cet email existe déjà");
        } else if (ensPhone.isPresent()) {
            throw new RessourceExistException("Ce numéro existe déjà");
        }
        Ecole ecole = this.ecoleRepo.findById(enseignantRequestDTO.getEcoleId())
                .orElseThrow(()-> new RessourceNotFoundException("School"+enseignantRequestDTO.getEcoleId()+"doesn't exist" ));
        ens.setEcole(ecole);

        List<Matiere> matieres = new ArrayList<>();
        for (Long id : enseignantRequestDTO.getMatieresIds()) {
            Matiere matiere = matiereRepo.findById(id).orElseThrow(() -> new RessourceNotFoundException("Material not found with id " + id));
            if (matiere.getEnseignant() != null) {
                throw new RessourceExistException("Course with ID " + id + " is already assigned to another teacher.");
            }
            matiere.setEnseignant(ens);
            matieres.add(matiere);
        }
        ens.setMatieres(matieres);

        Filiere filiere = this.filiereRepo.findById(enseignantRequestDTO.getFiliereId())
                .orElseThrow(()-> new RessourceNotFoundException("Filiere with id "+enseignantRequestDTO.getFiliereId()+"doesn't exsit" ));
        ens.setDepartment(filiere);

        ens.setCreatedAt(new Date());
        ens.setStatus("Actif");
        ens.setRole("ENSEIGNANT");
        ens.setPassword(this.encoder.encode(ens.getPassword()));
        return this.enseignantMapper.fromEnseignant(this.enseignantRepo.save(ens));
    }

    @Override
    public EnseignantResponseDTO getEnseignant(String email) throws RessourceNotFoundException {
        try {
            return this.enseignantMapper.fromEnseignant(this.enseignantRepo.findByEmail(email).get());
        }catch (Exception ex){
            throw new RessourceNotFoundException("this email : " +email+" doesn't exist in our data base !");
        }
    }

    @Override
    public List<EnseignantResponseDTO> getEnseignants() {
        List<Enseignant> enseignant = this.enseignantRepo.findAll();
        List<EnseignantResponseDTO> enseignantResponses = new ArrayList<>();
        enseignant.forEach(ens ->enseignantResponses.add(this.enseignantMapper.fromEnseignant(ens)));
        return enseignantResponses;
    }

    @Override
    public List<EnseignantResponseDTO> getEnseignantsByEcole(Long ecoleId) {
        List<Enseignant> enseignants = this.enseignantRepo.findAllByEcole(ecoleId);
        List<EnseignantResponseDTO> enseignantResponse = new ArrayList<>();
        enseignants.forEach(ens -> enseignantResponse.add(this.enseignantMapper.fromEnseignant(ens)));
        return enseignantResponse;
    }

    @Override
    public EnseignantResponseDTO updateEnseignant(String email, EnseignantRequestDTO enseignantRequestDTO) throws RessourceNotFoundException {
        try {
            Enseignant enseignant = this.enseignantMapper.fromEnseignantRequestDTO(enseignantRequestDTO);
            Enseignant newEnseignant = this.enseignantRepo.findByEmail(email).get();
            newEnseignant.setEmail(enseignant.getEmail());
            newEnseignant.setImage_url(enseignant.getImage_url());
            newEnseignant.setNom(enseignant.getNom());
            newEnseignant.setPrenom(enseignant.getPrenom());
            newEnseignant.setTelephone(enseignant.getTelephone());

            if (enseignant.getPassword().isEmpty()){
                newEnseignant.setPassword(newEnseignant.getPassword());
            } else newEnseignant.setPassword(this.encoder.encode(enseignant.getPassword()));

            List<Matiere> matieres = new ArrayList<>();
            for (Long id : enseignantRequestDTO.getMatieresIds()) {
                Matiere matiere = matiereRepo.findById(id).orElseThrow(() -> new RessourceNotFoundException("Material not found with id " + id));
                if (matiere.getEnseignant() != null) {
                    if (Objects.equals(matiere.getEnseignant().getId(), newEnseignant.getId())){
                        matiere.setEnseignant(newEnseignant);
                        matieres.add(matiere);
                    }else throw new RessourceExistException("Course with ID " + id + " is already assigned to another teacher.");
                } else {
                matiere.setEnseignant(newEnseignant);
                matieres.add(matiere);
                }
            }
            newEnseignant.setMatieres(matieres);

            Filiere filiere = this.filiereRepo.findById(enseignantRequestDTO.getFiliereId())
                    .orElseThrow(()-> new RessourceNotFoundException("Filiere with id "+enseignantRequestDTO.getFiliereId()+"doesn't exsit" ));
            newEnseignant.setDepartment(filiere);

            newEnseignant.setChiefDepartment(enseignant.isChiefDepartment());
            newEnseignant.setGenre(enseignant.getGenre());
            newEnseignant.setGrade(enseignant.getGrade());
            newEnseignant.setStatus(enseignant.getStatus());
            newEnseignant.setUpdatedAt(new Date());
            return this.enseignantMapper.fromEnseignant(this.enseignantRepo.save(newEnseignant));
        }catch (NoSuchElementException ex){
            throw new RessourceNotFoundException("this email : " +email+" doesn't exist in our data base !");
        }
    }

}
