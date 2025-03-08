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

    /**
     * It helps to user to register for Either of the role Admin or Staff
     * @param registrationRequest used to register the user.
     * @return user parent save the user data
     */
    @Override
    public UserResponse registerUser(RegistrationRequest registrationRequest) {
        User user=createUserByRole(registrationRequest.getRole());
        userMapper.mapToUserEntity(registrationRequest,user);

        this.userRepository.save(user);

        return userMapper.mapToUserResponse(user);
    }

    /**
     * Produces and return child instance of the user based on User role.
     * @param role the role of the user
     * @return user the parent reference containing either of Staff or Admin instances
     */
    private User createUserByRole(UserRole role){
        User user;
        switch(role){
            case ADMIN -> user=new Admin();
            case STAFF -> user= new Staff();
            default -> throw new RuntimeException("Failed to register user, Invalid user type");
        }
        return user;
    }

         /**
        * Based on id it helps to find user details
        * @param userId used to find the user data
        * @return the user parent reference containing the user details.
         */
        @Override
        public UserResponse findUserById(User userId){
             return userRepository.findById(userId.getUserId())
                     .map(userMapper::mapToUserResponse)
                    .orElseThrow(() -> new UserNotFoundByIdException("Failed to find user, user not found by id"));
        }

    /**
     * It helps to return the user to update the specified data
      * @param userRequest it used to
     * @param userId it helps to get the user data
     * @return user parent reference to update the user specified data
     */
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
