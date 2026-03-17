package com.genus.GENUS_SUP.Mapper;

import com.genus.GENUS_SUP.Entity.User;
import com.genus.GENUS_SUP.dto.user_dto.UserRequest;
import com.genus.GENUS_SUP.dto.user_dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;


@Mapper(componentModel = "spring")
@Configuration
public interface UserMapper {

    User fromUserRequest (UserRequest userRequest);

    @Mapping(source = "ecole", target = "ecole")
    UserResponse fromUser (User user);
}
