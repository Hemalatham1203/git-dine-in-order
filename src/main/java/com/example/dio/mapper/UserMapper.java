package com.example.dio.mapper;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     *This method is typically used to convert an entity or domain model (User) to a response object (UserResponse) that can be sent as a response in an API.
     * @param user  object that contains the data to be mapped.
     * @return user response object containing the relevant data from the User object, often with sensitive information omitted.
     */
      UserResponse mapToUserResponse(User user);

    /**
     * @param registrationRequest containing user registration details (e.g., username, email, password) to be mapped to the User entity.
     * @param user entity that will be updated with the values from the registrationRequest.
     *  method doesn't return anything, it directly modifies the User entity passed in as a parameter.
     */
     void mapToUserEntity(RegistrationRequest registrationRequest, @MappingTarget User user);

    /**
     * @param userRequest containing the user data (e.g., username, email, etc.) to be mapped to the existing User entity.
     * @param user entity that will be updated with the values from the userRequest. The @MappingTarget annotation indicates that this object will be modified by the mapping.
     */
     void mapToUserEntity(UserRequest userRequest,@MappingTarget User user);

}
