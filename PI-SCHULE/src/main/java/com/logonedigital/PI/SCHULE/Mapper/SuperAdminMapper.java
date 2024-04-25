package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.SuperAdmin;
import com.logonedigital.PI.SCHULE.dto.superAdmin_dto.SuperAdminRequest;
import com.logonedigital.PI.SCHULE.dto.superAdmin_dto.SuperAdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface SuperAdminMapper {
    SuperAdmin fromSuperAdminRequest (SuperAdminRequest superAdminRequest);

    SuperAdminResponse fromSuperAdmin (SuperAdmin superAdmin);

}
