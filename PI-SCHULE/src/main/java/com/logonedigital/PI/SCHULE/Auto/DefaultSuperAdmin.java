package com.logonedigital.PI.SCHULE.Auto;

import com.logonedigital.PI.SCHULE.Entity.SuperAdmin;
import com.logonedigital.PI.SCHULE.Mapper.SuperAdminMapper;
import com.logonedigital.PI.SCHULE.Repository.SuperAdminRepository;
import com.logonedigital.PI.SCHULE.dto.superAdmin_dto.SuperAdminRequest;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
