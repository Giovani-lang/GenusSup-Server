package com.genus.GENUS_PRIMO.Service;

import com.genus.GENUS_PRIMO.Entity.*;
import com.genus.GENUS_PRIMO.Exception.RessourceExistException;
import com.genus.GENUS_PRIMO.Exception.RessourceNotFoundException;
import com.genus.GENUS_PRIMO.Mapper.ParentMapper;
import com.genus.GENUS_PRIMO.Repository.EtudiantRepository;
import com.genus.GENUS_PRIMO.Repository.ParentRepository;
import com.genus.GENUS_PRIMO.Service.Interface.IParentService;
import com.genus.GENUS_PRIMO.dto.parent_dto.ParentRequest;
import com.genus.GENUS_PRIMO.dto.parent_dto.ParentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements IParentService {
    private final ParentRepository parentRepo;
    private final ParentMapper parentMapper;
    private final EtudiantRepository etudiantRepo;




    @Override
    public ParentResponse addParent(ParentRequest parentRequest) {
        Parent parent = this.parentMapper.fromTuteurRequest(parentRequest);
        Optional<Parent> parentEmail = this.parentRepo.findByEmail(parentRequest.getEmail());
        Optional<Parent> parentNumber = this.parentRepo.findByTelephone(parentRequest.getTelephone());
        if (parentEmail.isPresent()){
            throw new RessourceExistException("Cet email existe déjà");
        } else if (parentNumber.isPresent()) {
            throw new RessourceExistException("Ce numéro existe déjà");
        }
        Etudiant etudiant = etudiantRepo.findById(parentRequest.getEtudiantId())
                .orElseThrow(() -> new RessourceNotFoundException("Etudiant not found with id " + parentRequest.getEtudiantId()));
        parent.setEtudiant(etudiant);
        parent.setCreatedAt(new Date());
        parent.setUsername(parent.getEmail());
        parent.setRole("PARENT");
        return this.parentMapper.fromTuteur(this.parentRepo.save(parent));
    }

    @Override
    public ParentResponse editParent(String email, ParentRequest parentRequest) {
        try {
            Parent parent = this.parentMapper.fromTuteurRequest(parentRequest);
            Parent parentUpdated = this.parentRepo.findByEmail(email).get();
            parentUpdated.setNom(parent.getNom());
            parentUpdated.setEmail(parent.getEmail());
            parentUpdated.setUsername(parent.getEmail());
            parentUpdated.setPrenom(parent.getPrenom());

            Etudiant etudiant = etudiantRepo.findById(parentRequest.getEtudiantId())
                    .orElseThrow(() -> new RessourceNotFoundException("Etudiant not found with id " + parentRequest.getEtudiantId()));
            parentUpdated.setEtudiant(etudiant);
            parentUpdated.setAdresse(parent.getAdresse());
            parentUpdated.setTelephone(parent.getTelephone());
            parentUpdated.setGenre(parent.getGenre());
            parentUpdated.setProfession(parent.getProfession());
            parentUpdated.setUpdatedAt(new Date());
            return this.parentMapper.fromTuteur(this.parentRepo.saveAndFlush(parentUpdated));
        }catch (NoSuchElementException exception) {
            throw new RessourceNotFoundException("This email " +email+ " doesn't exist in our data base !");
        }
    }

    @Override
    public List<ParentResponse> search(Long ecoleId) {
        List<Parent> parents = this.parentRepo.findAll();
        List<ParentResponse> parentRespons = new ArrayList<>();
        parents.forEach(admin -> parentRespons.add(this.parentMapper.fromTuteur(admin)));
        return parentRespons;
    }

    @Override
    public ParentResponse getParent(String email) {
        try {
            return this.parentMapper.fromTuteur(this.parentRepo.findByEmail(email).get());
        }catch (Exception ex){
            throw new RessourceNotFoundException("This email " +email+ " doesn't exist in our data base !");
        }
    }
}
