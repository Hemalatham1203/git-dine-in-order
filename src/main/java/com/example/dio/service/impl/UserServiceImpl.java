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
        User user2=this.createUserByRole(user.getRole());

        this.mapToNewUser(user);

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

    private  void mapToNewUser(User user) {
        User user2;
        switch(user.getRole()){
            case ADMIN -> user2=new Admin();
            case STAFF -> user2= new Staff();
            default -> throw new RuntimeException("Failed to register user, Invalid user type");
        }
    }
}
