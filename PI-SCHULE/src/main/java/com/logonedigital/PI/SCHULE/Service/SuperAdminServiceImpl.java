package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.SuperAdmin;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.SuperAdminMapper;
import com.logonedigital.PI.SCHULE.Repository.SuperAdminRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.ISuperAdminService;
import com.logonedigital.PI.SCHULE.dto.superAdmin_dto.SuperAdminRequest;
import com.logonedigital.PI.SCHULE.dto.superAdmin_dto.SuperAdminResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SuperAdminServiceImpl implements ISuperAdminService {
    private final SuperAdminRepository superAdminRepo;
    private final SuperAdminMapper superAdminMapper;
    private final PasswordEncoder encoder;

    @Override
    public SuperAdminResponse addSuperAdmin(SuperAdminRequest superAdminRequest) {
        SuperAdmin superAdmin = this.superAdminMapper.fromSuperAdminRequest(superAdminRequest);
        Optional<SuperAdmin> superAdminEmail = this.superAdminRepo.findByEmail(superAdminRequest.getEmail());
        Optional<SuperAdmin> superAdminPhone = this.superAdminRepo.findByTelephone(superAdminRequest.getTelephone());
        if (superAdminEmail.isPresent()){
            throw new RessourceExistException("Cet email existe déjà");
        } else if (superAdminPhone.isPresent()) {
            throw new RessourceExistException("Ce numéro existe déjà");
        }
        superAdmin.setPassword(encoder.encode(superAdmin.getPassword()));
        superAdmin.setStatus("Actif");
        superAdmin.setRole("SUPER_ADMIN");
        superAdmin.setCreatedAt(new Date());
        return this.superAdminMapper.fromSuperAdmin(this.superAdminRepo.save(superAdmin));
    }

    @Override
    public SuperAdminResponse getSuperAdmin(Long id) {
        return this.superAdminMapper.fromSuperAdmin(this.superAdminRepo.findById(id).get());
    }

    @Override
    public List<SuperAdminResponse> getAllSuperAdmin() {
        List<SuperAdmin> superAdmins = this.superAdminRepo.findAll();
        List<SuperAdminResponse> SuperAdminResponses = new ArrayList<>();
        superAdmins.forEach(superAdmin -> SuperAdminResponses.add(this.superAdminMapper.fromSuperAdmin(superAdmin)));
        return SuperAdminResponses;
    }

    @Override
    public SuperAdminResponse editSuperAdmin(String email, SuperAdminRequest superAdmin) {
        try{
            SuperAdmin newSuperAdmin = this.superAdminRepo.findByEmail(email).get();
            newSuperAdmin.setImage_url(superAdmin.getImage_url());
            newSuperAdmin.setPrenom(superAdmin.getPrenom());
            newSuperAdmin.setNom(superAdmin.getNom());
            newSuperAdmin.setEmail(superAdmin.getEmail());

            if(superAdmin.getPassword().isEmpty()){
                newSuperAdmin.setPassword(newSuperAdmin.getPassword());
            } else newSuperAdmin.setPassword(encoder.encode(superAdmin.getPassword()));

            newSuperAdmin.setStatus(superAdmin.getStatus());
            newSuperAdmin.setGenre(superAdmin.getGenre());
            newSuperAdmin.setTelephone(superAdmin.getTelephone());
            newSuperAdmin.setUpdatedAt(new Date());
            return this.superAdminMapper.fromSuperAdmin(this.superAdminRepo.saveAndFlush(newSuperAdmin));
        }catch (NoSuchElementException ex){
            throw new RessourceNotFoundException("This email " +email+ " doesn't exist in our data base !");
        }
    }
}
