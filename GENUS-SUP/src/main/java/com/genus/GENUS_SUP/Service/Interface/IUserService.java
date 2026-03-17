package com.genus.GENUS_SUP.Service.Interface;

import com.genus.GENUS_SUP.dto.user_dto.UserRequest;
import com.genus.GENUS_SUP.dto.user_dto.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse addUser (UserRequest user);
//    UserResponse getUser(String email);
    UserResponse findByUsername(String username);
    List<UserResponse> getAllUser();
    UserResponse editUser(String username, UserRequest user);
}
