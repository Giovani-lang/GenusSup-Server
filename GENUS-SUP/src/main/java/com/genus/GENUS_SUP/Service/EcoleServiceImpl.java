package com.genus.GENUS_SUP.Service;

import com.genus.GENUS_SUP.Entity.Ecole;
import com.genus.GENUS_SUP.Entity.SuperAdmin;
import com.genus.GENUS_SUP.Exception.RessourceExistException;
import com.genus.GENUS_SUP.Exception.RessourceNotFoundException;
import com.genus.GENUS_SUP.Mapper.EcoleMapper;
import com.genus.GENUS_SUP.Mapper.SuperAdminMapper;
import com.genus.GENUS_SUP.Repository.EcoleRepository;
import com.genus.GENUS_SUP.Repository.SuperAdminRepository;
import com.genus.GENUS_SUP.Service.Interface.IEcoleService;
import com.genus.GENUS_SUP.dto.ecole_dto.EcoleRequest;
import com.genus.GENUS_SUP.dto.ecole_dto.EcoleResponse;
import com.genus.GENUS_SUP.dto.superAdmin_dto.SuperAdminRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EcoleServiceImpl implements IEcoleService {
    private final EcoleRepository ecoleRepo;
    private final EcoleMapper ecoleMapper;
    private final SuperAdminRepository superAdminRepo;
    private final SuperAdminMapper superAdminMapper;
    private final PasswordEncoder encoder;
    private final EmailService emailService;


    public String defaultEmail(){
        String prefix = "genus.access";
        String suffix = String.format("%03d", new Random().nextInt(1000));
        return  prefix + suffix +"@gmail.com";
    }
    @Override
    public EcoleResponse addEcole(EcoleRequest ecoleRequest) {
        Ecole ecole = this.ecoleMapper.fromEcoleRequest(ecoleRequest);

        Optional<Ecole> eco1 = this.ecoleRepo.findByEmail(ecoleRequest.getEmail());
        Optional<Ecole> eco2 = this.ecoleRepo.findByPhone(ecoleRequest.getPhone());
        Optional<Ecole> eco3 = this.ecoleRepo.findByFax(ecoleRequest.getFax());
        Optional<Ecole> eco4 = this.ecoleRepo.findByName(ecoleRequest.getName());
        if (eco1.isPresent()){
            throw new RessourceExistException("Email already exist !!!");
        } else if (eco2.isPresent()) {
            throw new RessourceExistException("Phone number already exist !!!");
        } else if (eco3.isPresent()) {
            throw new RessourceExistException("Fax already exist !!!");
        } else if (eco4.isPresent()) {
            throw new RessourceExistException("This school already exist !!!");
        }
        ecole.setCreatedAt(new Date());
        EcoleResponse ecoleSaved = this.ecoleMapper.fromEcole(this.ecoleRepo.save(ecole));

        //** Création de le super admin par défaut au moment de l'enregistrement d'une nouvelle école **
        SuperAdmin savedSuperAdmin = createDefaultSuperAdmin(ecole);

        return ecoleSaved;
    }

    @Override
    public EcoleResponse getEcole(Long id) {
        try{
            return this.ecoleMapper.fromEcole(this.ecoleRepo.findById(id).get());
        }catch(Exception e){
            throw new RessourceNotFoundException("Not found, try again");
        }
    }

    @Override
    public List<EcoleResponse> getAllEcole() {
        List<Ecole> ecoles = this.ecoleRepo.findAll();
        List<EcoleResponse> ecoleResponses = new ArrayList<>();
        ecoles.forEach(ecole -> ecoleResponses.add(this.ecoleMapper.fromEcole(ecole)));
        return ecoleResponses;
    }

    @Override
    public EcoleResponse editEcole(Long id, EcoleRequest ecole) throws RessourceNotFoundException {
       try{
           Ecole newEcole = this.ecoleRepo.findById(id).get();

           newEcole.setImage_url(ecole.getImage_url());
           newEcole.setAddress(ecole.getAddress());
           newEcole.setAcronym(ecole.getAcronym());
           newEcole.setFax(ecole.getFax());
           newEcole.setEmail(ecole.getEmail());
           newEcole.setSite(ecole.getSite());
           newEcole.setCity(ecole.getCity());
           newEcole.setCountry(ecole.getCountry());
           newEcole.setResponsible(ecole.getResponsible());
           newEcole.setResponsible_phone(ecole.getResponsible_phone());
           newEcole.setPo_box(ecole.getPo_box());
           newEcole.setPhone(ecole.getPhone());
           newEcole.setUpdatedAt(new Date());

           return this.ecoleMapper.fromEcole(this.ecoleRepo.saveAndFlush(newEcole));
       }catch(NoSuchElementException e){
           throw new RessourceNotFoundException ("Not found, try again");
       }
    }


    //** Méthode de création du super admin par défaut au moment de l'enregistrement d'une noulle école **
    @Transactional
    private SuperAdmin createDefaultSuperAdmin(Ecole ecole) {
        SuperAdminRequest superAdminRequest = SuperAdminRequest.build(
                null,
                null,
                null,
                null,
                this.encoder.encode("admin"),
                null,
                null,
                "Actif",
                null,
                ecole.getId()
        );
        SuperAdmin superAdmin = this.superAdminMapper.fromSuperAdminRequest(superAdminRequest);
        superAdmin.setUsername(defaultEmail());
        superAdmin.setRole("SUPER_ADMIN");
        superAdmin.setEcole(ecole);
        superAdmin.setCreatedAt(new Date());
        SuperAdmin save = this.superAdminRepo.save(superAdmin);
        this.emailService.sendAdminNotificationEmail(save);
        return save;
    }

}
