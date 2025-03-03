package com.example.dio.controller;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        UserResponse response = userService.registerUser(registrationRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "User created",response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable long userId) {
        UserResponse response=userService.findUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK,"user Found", response);
    }

    @PutMapping("/users/update/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@RequestBody UserRequest userRequest,@PathVariable long userId){
        UserResponse response=userService.updateUserById(userRequest,userId);
        return ResponseBuilder.success(HttpStatus.OK,"User Updated", response);
    }
}


