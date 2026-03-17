package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Founder;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.FounderMapper;
import com.genus.GENUS_SUP.Repository.FounderRepository;
import com.genus.GENUS_SUP.Service.Interface.IFounderService;
import com.genus.GENUS_SUP.dto.founder_dto.FounderRequest;
import com.genus.GENUS_SUP.dto.founder_dto.FounderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FounderServiceImpl implements IFounderService {
    private final FounderRepository founderRepo;
    private final FounderMapper founderMapper;
    private final PasswordEncoder encoder;

    @Override
    public FounderResponse addFounder(FounderRequest founderRequest) {
        Founder founder = this.founderMapper.fromFounderRequest(founderRequest);
        Optional<Founder> founderEmail = this.founderRepo.findByEmail(founderRequest.getEmail());
        Optional<Founder> founderPhone = this.founderRepo.findByTelephone(founderRequest.getTelephone());
        if (founderEmail.isPresent()){
            throw new RessourceExistException("Cet email existe déjà");
        } else if (founderPhone.isPresent()) {
            throw new RessourceExistException("Ce numéro existe déjà");
        }
        founder.setPassword(encoder.encode(founder.getPassword()));
        founder.setStatus("Actif");
        founder.setRole("FONDATEUR");
        founder.setCreatedAt(new Date());
        return this.founderMapper.fromFounder(this.founderRepo.save(founder));
    }

    @Override
    public FounderResponse getFounder(Long id) {
        return this.founderMapper.fromFounder(this.founderRepo.findById(id).get());
    }

    @Override
    public List<FounderResponse> getAllFounders() {
        List<Founder> founders = this.founderRepo.findAll();
        List<FounderResponse> founderResponses = new ArrayList<>();
        founders.forEach(founder -> founderResponses.add(this.founderMapper.fromFounder(founder)));
        return founderResponses;
    }

    @Override
    public FounderResponse editFounder(String email, FounderRequest oldFounder) {
        try{
            Founder newFounder = this.founderRepo.findByEmail(email).get();
            newFounder.setImage_url(oldFounder.getImage_url());
            newFounder.setPrenom(oldFounder.getPrenom());
            newFounder.setNom(oldFounder.getNom());
            newFounder.setEmail(oldFounder.getEmail());
            newFounder.setUsername(oldFounder.getEmail());

            if(oldFounder.getPassword().isEmpty()){
                newFounder.setPassword(newFounder.getPassword());
            } else newFounder.setPassword(encoder.encode(oldFounder.getPassword()));

            newFounder.setStatus(oldFounder.getStatus());
            newFounder.setGenre(oldFounder.getGenre());
            newFounder.setTelephone(oldFounder.getTelephone());
            newFounder.setUpdatedAt(new Date());
            return this.founderMapper.fromFounder(this.founderRepo.saveAndFlush(newFounder));
        }catch (NoSuchElementException ex){
            throw new RessourceNotFoundException("This email " +email+ " doesn't exist in our data base !");
        }
    }
}
