package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Administration;
import com.genus.GENUS_SUP.Entity.Ecole;
import com.genus.GENUS_SUP.Mapper.AdminMapper;
import com.genus.GENUS_SUP.dto.admin_dto.AdminRequestDTO;
import com.genus.GENUS_SUP.dto.admin_dto.AdminResponseDTO;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Repository.AdminRepo;
import com.genus.GENUS_SUP.Repository.EcoleRepository;
import com.genus.GENUS_SUP.Service.Interface.IAdminService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {
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
        admin.setUsername(admin.getEmail());
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
            adminUpdated.setUsername(admin.getEmail());
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

