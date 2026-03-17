package com.genus.GENUS_SUP.Controller;


import com.genus.GENUS_SUP.Service.Interface.IUserService;
import com.genus.GENUS_SUP.dto.user_dto.UserRequest;
import com.genus.GENUS_SUP.dto.user_dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser (@RequestBody @Valid UserRequest user){
        return new ResponseEntity<>(this.userService.addUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/detail/{username}")
    public ResponseEntity<UserResponse> getUser (@PathVariable(name = "username") String username){
        return new ResponseEntity<>(this.userService.findByUsername(username), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> getAllUser (){
        return new ResponseEntity<>(this.userService.getAllUser(), HttpStatus.OK);
    }
    @PutMapping("/edit/{username}")
    public ResponseEntity<UserResponse> editUser (@PathVariable(name = "username") String username,@RequestBody  UserRequest user){
        return new ResponseEntity<>(this.userService.editUser(username,user), HttpStatus.ACCEPTED);
    }

}
