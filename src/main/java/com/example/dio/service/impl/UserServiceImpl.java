package com.example.dio.service.impl;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) {
        User user=createUserByRole(registrationRequest.getRole());
        userMapper.mapToUserEntity(registrationRequest,user);

        this.userRepository.save(user);

        return userMapper.mapToUserResponse(user);
    }



    private User createUserByRole(UserRole role){
        User user;
        switch(role){
            case ADMIN -> user=new Admin();
            case STAFF -> user= new Staff();
            default -> throw new RuntimeException("Failed to register user, Invalid user type");
        }
        return user;
    }


        @Override
        public UserResponse findUserById(long userId){
             return userRepository.findById(userId)
                     .map(userMapper::mapToUserResponse)
                    .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user, user not found by id"));
        }

    @Override
    public UserResponse updateUserById(UserRequest userRequest, long userId) {
        return userRepository.findById(userId)
                .map(exUser -> {
                    userMapper.mapToUserEntity(userRequest, exUser);
                    userRepository.save(exUser);
                    return userMapper.mapToUserResponse(exUser);
                })
                .orElseThrow(() -> new UserNotFoundByIdException("Failed to update user, user not found by ID"));
    }



}
