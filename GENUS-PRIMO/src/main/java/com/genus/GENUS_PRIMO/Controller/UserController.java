package com.genus.GENUS_PRIMO.Controller;


import com.genus.GENUS_PRIMO.Service.Interface.IUserService;
import com.genus.GENUS_PRIMO.dto.user_dto.UserRequest;
import com.genus.GENUS_PRIMO.dto.user_dto.UserResponse;
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
    @GetMapping("/detail/{email}")
    public ResponseEntity<UserResponse> getUser (@PathVariable(name = "email") String email){
        return new ResponseEntity<>(this.userService.getUser(email), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> getAllUser (){
        return new ResponseEntity<>(this.userService.getAllUser(), HttpStatus.OK);
    }
    @PutMapping("/edit/{email}")
    public ResponseEntity<UserResponse> editUser (@PathVariable(name = "email") String email,@RequestBody  UserRequest user){
        return new ResponseEntity<>(this.userService.editUser(email,user), HttpStatus.ACCEPTED);
    }

}
