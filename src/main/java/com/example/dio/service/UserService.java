package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
   public UserResponse  registerUser(RegistrationRequest registrationRequest);

    UserResponse findUserById(long userId);

    UserResponse updateUserById(UserRequest userRequest,long userId);
}
