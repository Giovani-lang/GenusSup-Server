package com.logonedigital.PI.SCHULE.Service.Interface;

import com.logonedigital.PI.SCHULE.Entity.User;
import com.logonedigital.PI.SCHULE.dto.user_dto.UserRequest;
import com.logonedigital.PI.SCHULE.dto.user_dto.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse addUser (UserRequest user);
    UserResponse getUser(String email);
    List<UserResponse> getAllUser();
    UserResponse editUser(String email, UserRequest user);
}
