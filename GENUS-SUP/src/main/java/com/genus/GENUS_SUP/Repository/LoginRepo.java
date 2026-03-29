package com.genus.GENUS_SUP.Repository;

import com.genus.GENUS_SUP.Entity.User;
import com.genus.GENUS_SUP.dto.login_dto.LoginDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepo extends JpaRepository <LoginDTO, String> {
    Optional<User> findUserByUsername(String username);
}
