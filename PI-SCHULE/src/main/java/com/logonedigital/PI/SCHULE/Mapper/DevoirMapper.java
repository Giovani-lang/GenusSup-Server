package com.logonedigital.PI.SCHULE.Mapper;

import com.logonedigital.PI.SCHULE.Entity.Devoir;
import com.logonedigital.PI.SCHULE.dto.devoir_dto.DevoirRequest;
import com.logonedigital.PI.SCHULE.dto.devoir_dto.DevoirResponse;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
@Configuration
public interface DevoirMapper {
    Devoir fromDevoirRequest (DevoirRequest devoirRequest);

    DevoirResponse fromDevoir (Devoir devoir);
}
