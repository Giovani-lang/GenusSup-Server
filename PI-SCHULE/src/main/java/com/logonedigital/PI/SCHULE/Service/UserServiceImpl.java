package com.logonedigital.PI.SCHULE.Service;

import com.logonedigital.PI.SCHULE.Entity.User;
import com.logonedigital.PI.SCHULE.Exception.RessourceExistException;
import com.logonedigital.PI.SCHULE.Exception.RessourceNotFoundException;
import com.logonedigital.PI.SCHULE.Mapper.UserMapper;
import com.logonedigital.PI.SCHULE.Repository.UserRepository;
import com.logonedigital.PI.SCHULE.Service.Interface.IUserService;
import com.logonedigital.PI.SCHULE.dto.user_dto.UserRequest;
import com.logonedigital.PI.SCHULE.dto.user_dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;


    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = this.userMapper.fromUserRequest(userRequest);
        Optional<User> user1 = this.userRepo.findUserByEmail(userRequest.getEmail());
        if (user1.isPresent()){
            throw new RessourceExistException("This user already exist");
        }
        return this.userMapper.fromUser(this.userRepo.save(user));
    }

    @Override
    public UserResponse getUser(String email)throws RessourceNotFoundException{
        try {
            return this.userMapper.fromUser(this.userRepo.findUserByEmail(email).get());
        }catch (Exception ex){
            throw new RessourceNotFoundException("This user doesn't exist");
        }
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(this.userMapper.fromUser(user)));
        return userResponses;
    }

    @Override
    public UserResponse editUser(String email, UserRequest user)throws RessourceNotFoundException {
        try {
            User newUser = this.userRepo.findUserByEmail(email).get();
            User oldUser = this.userMapper.fromUserRequest(user);
            newUser.setEmail(oldUser.getEmail());
            newUser.setNom(oldUser.getNom());
            newUser.setPrenom(oldUser.getPrenom());

            if(oldUser.getPassword().isEmpty() || oldUser.getPassword() == null ){
                newUser.setPassword(newUser.getPassword());
            }else newUser.setPassword(this.encoder.encode(oldUser.getPassword()));

            newUser.setTelephone(oldUser.getTelephone());
            newUser.setRole(oldUser.getRole());
            newUser.setGenre(oldUser.getGenre());
            newUser.setFirstLogin(oldUser.isFirstLogin());

            return this.userMapper.fromUser(this.userRepo.saveAndFlush(newUser));
        }catch (NoSuchElementException ex){
            throw new RessourceNotFoundException("This user doesn't exist");
        }
    }

}
