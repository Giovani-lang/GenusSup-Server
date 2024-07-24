package com.genus.GENUS_PRIMO.Mapper;

import com.genus.GENUS_PRIMO.Entity.SuperAdmin;
import com.genus.GENUS_PRIMO.dto.superAdmin_dto.SuperAdminRequest;
import com.genus.GENUS_PRIMO.dto.superAdmin_dto.SuperAdminResponse;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface SuperAdminMapper {
    SuperAdmin fromSuperAdminRequest (SuperAdminRequest superAdminRequest);

    SuperAdminResponse fromSuperAdmin (SuperAdmin superAdmin);

}
