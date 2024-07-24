package com.genus.GENUS_PRIMO.Service.Interface;

import com.genus.GENUS_PRIMO.dto.user_dto.UserRequest;
import com.genus.GENUS_PRIMO.dto.user_dto.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse addUser (UserRequest user);
    UserResponse getUser(String email);
    UserResponse findByUsername(String username);
    List<UserResponse> getAllUser();
    UserResponse editUser(String email, UserRequest user);
}
