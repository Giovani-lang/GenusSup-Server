package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Administration;
import com.genus.GENUS_SUP.dto.admin_dto.AdminRequestDTO;
import com.genus.GENUS_SUP.dto.admin_dto.AdminResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);
    Administration fromAdminRequestDTO(AdminRequestDTO adminRequestDTO);

    @Mapping(source = "ecole", target = "ecole")
    AdminResponseDTO fromAdministration(Administration administration);
}
