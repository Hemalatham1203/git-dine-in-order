package com.example.dio.service.impl;

import com.example.dio.enums.UserRole;
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
    @Override
    public User registerUser(User user) {
        User user2=createUserByRole(user.getRole());

        this.mapToNewUser(user,user2);
        return userRepository.save(user2);
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

    private  void mapToNewUser(User user,User user2) {
        user2.setUsername(user.getUsername());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setRole(user.getRole());
        user2.setPhno(user.getPhno());

        }



}
