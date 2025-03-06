package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**It helps the user to register to the either of the role staff or admin
     * @param registrationRequest used to register the user.
     * @return  Object to register the user
     */
   public UserResponse  registerUser(RegistrationRequest registrationRequest);

    /**
     * It helps to find the user based on userid
     * @param userId  to find user details
     * @return user reference containing user details
     */
    UserResponse findUserById(long userId);

    /**
     * It helps to update the user specified data based on user id
     * @param userRequest  request of the user to update the existing user
     * @param userId user to return
     */
    UserResponse updateUserById(UserRequest userRequest,long userId);
}
