package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.Bulletin;
import com.genus.GENUS_SUP.dto.bulletin_dto.BulletinRequest;
import com.genus.GENUS_SUP.dto.bulletin_dto.BulletinResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface BulletinMapper {

    Bulletin fromBulletinRequest (BulletinRequest bulletinRequest);

    @Mapping(source = "enseignant", target = "enseignant")
    @Mapping(source = "anneeAcademique", target = "anneeAcademique")
    BulletinResponse fromBulletin (Bulletin bulletin);
}
