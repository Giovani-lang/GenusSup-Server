package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.Administration;
import com.logonedigital.PI.SCHULE.Entity.Ecole;
import com.logonedigital.PI.SCHULE.Entity.Etudiant;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.AdminMapper;
import com.logonedigital.PI.SCHULE.Repository.AdminRepo;
import com.logonedigital.PI.SCHULE.Repository.EcoleRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.AdminService;
import com.logonedigital.PI.SCHULE.dto.admin_dto.AdminRequestDTO;
import com.logonedigital.PI.SCHULE.dto.admin_dto.AdminResponseDTO;
import com.logonedigital.PI.SCHULE.dto.etudiant_dto.EtudiantResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;
    private final AdminMapper adminMapper;
    private final PasswordEncoder encoder;
    private final EcoleRepository ecoleRepo;

    @Override
    public AdminResponseDTO addAdministration(AdminRequestDTO adminRequestDTO) throws RessourceExistException {
        Administration admin = this.adminMapper.fromAdminRequestDTO(adminRequestDTO);
        Optional<Administration> adminEmail = this.adminRepo.findByEmail(adminRequestDTO.getEmail());
        Optional<Administration> adminPhone = this.adminRepo.findByTelephone(adminRequestDTO.getTelephone());
        if (adminEmail.isPresent()){
            throw new RessourceExistException("Cet email existe déjà");
        } else if (adminPhone.isPresent()) {
            throw new RessourceExistException("Ce numéro existe déjà");
        }
        Ecole ecole = this.ecoleRepo.findById(adminRequestDTO.getEcoleId())
                .orElseThrow(()-> new RessourceNotFoundException("School"+adminRequestDTO.getEcoleId()+"doesn't exsit" ));
        admin.setEcole(ecole);
        admin.setRole("ADMIN");
        admin.setStatus("Actif");
        admin.setCreatedAt(new Date());
        admin.setPassword(this.encoder.encode(admin.getPassword()));
        return this.adminMapper.fromAdministration(this.adminRepo.save(admin));
    }

    @Override
    public List<AdminResponseDTO> getAdministrations() {
        List<Administration> admins= this.adminRepo.findAll();
        List<AdminResponseDTO> adminResponses = new ArrayList<>();
        admins.forEach(admin ->adminResponses.add(this.adminMapper.fromAdministration(admin)));
        return adminResponses;
    }

    @Override
    public List<AdminResponseDTO> getAdministrationsByEcole(Long ecoleId) {
        List<Administration> administrations = this.adminRepo.findAllByEcole(ecoleId);
        List<AdminResponseDTO> adminResponses = new ArrayList<>();
        administrations.forEach(admin -> adminResponses.add(this.adminMapper.fromAdministration(admin)));
        return adminResponses;
    }

    @Override
    public AdminResponseDTO getAdministration(String email) throws RessourceNotFoundException{
        try {
            return this.adminMapper.fromAdministration(this.adminRepo.findByEmail(email).get());
        }catch (Exception ex){
            throw new RessourceNotFoundException("This email " +email+ " doesn't exist in our data base !");
        }
    }

    @Override
    public AdminResponseDTO updateAdministration(String email, AdminRequestDTO adminRequestDTO) throws RessourceNotFoundException {
        try {
            Administration admin = this.adminMapper.fromAdminRequestDTO(adminRequestDTO);
            Administration adminUpdated = this.adminRepo.findByEmail(email).get();
            adminUpdated.setImage_url(admin.getImage_url());
            adminUpdated.setNom(admin.getNom());
            adminUpdated.setEmail(admin.getEmail());
            adminUpdated.setPrenom(admin.getPrenom());

            if (admin.getPassword().isEmpty()){
                adminUpdated.setPassword(adminUpdated.getPassword());
            } else adminUpdated.setPassword(this.encoder.encode(admin.getPassword()));

            adminUpdated.setTelephone(admin.getTelephone());
            adminUpdated.setGenre(admin.getGenre());
            adminUpdated.setPoste(admin.getPoste());
            adminUpdated.setStatus(admin.getStatus());
            adminUpdated.setUpdatedAt(new Date());
            return this.adminMapper.fromAdministration(this.adminRepo.saveAndFlush(adminUpdated));
        }catch (NoSuchElementException exception) {
            throw new RessourceNotFoundException("This email " +email+ " doesn't exist in our data base !");
        }
    }

}

