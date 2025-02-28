package com.example.dio.service;

import com.example.dio.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(User user);

    User findUserById(long userId);


}
