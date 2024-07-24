package com.genus.GENUS_PRIMO.Auto;

import com.genus.GENUS_PRIMO.Entity.SuperAdmin;
import com.genus.GENUS_PRIMO.Mapper.SuperAdminMapper;
import com.genus.GENUS_PRIMO.Repository.SuperAdminRepository;
import com.genus.GENUS_PRIMO.dto.superAdmin_dto.SuperAdminRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@RequiredArgsConstructor
public class DefaultSuperAdmin {

    private final PasswordEncoder encoder;
    private final SuperAdminRepository superAdminRepo;
    private final SuperAdminMapper superAdminMapper;

@PostConstruct
public void init() {
    long count = superAdminRepo.countBySuperAdmin();
    if (count == 0) {
        SuperAdminRequest superAdminRequest = new SuperAdminRequest(
                null,
                null,
                "super.admin@gmail.com",
                null,
                this.encoder.encode("super admin"),
                null,
                "Actif",
                "--"

        );
        SuperAdmin superAdmin = this.superAdminMapper.fromSuperAdminRequest(superAdminRequest);
        superAdmin.setRole("SUPER_ADMIN");
        superAdmin.setCreatedAt(new Date());
        this.superAdminRepo.save(superAdmin);
    }
}
}
