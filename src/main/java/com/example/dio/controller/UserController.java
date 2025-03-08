package com.example.dio.controller;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("${app.base-url}")
@RestController
@AllArgsConstructor
@Tag(name="User Controller",description = "Collection API Endpoints dealing user data.")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(description = """
            The API Endpoint is used to register the User.
            The Endpoint requires the user to select one of the specified role along with the other details.
            """,responses = {
            @ApiResponse(responseCode = "201",description = "user Created"),
            @ApiResponse(responseCode = "400",description = "Invalid Input",
            content = {
                    @Content(schema = @Schema(implementation = FieldErrorResponse.class))
            })
    })
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserResponse response = userService.registerUser(registrationRequest);
        return ResponseBuilder.success(HttpStatus.CREATED, "User created",response);
    }

    @GetMapping("/users/{userId}")
    @Operation(description = """
            The API endpoint used to find the user.
            The endpoint requires the user to enter the id to find the details.
            """,responses = {
            @ApiResponse(responseCode = "201",description = "user found"),
            @ApiResponse(responseCode = "400",description = "User not found",
                    content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
    })
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable User userId) {
        UserResponse response=userService.findUserById(userId);
        return ResponseBuilder.ok( response,"user Found");
    }

    @PutMapping("/users/{userId}")
    @Operation(description = """
            The API endpoint used to update the User.
            The user required to enter the id based on the id user can update specific details such as.
            """, responses = {
            @ApiResponse(responseCode = "201", description = "user updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Input",
                    content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
            })
    })

    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@RequestBody UserRequest userRequest,@PathVariable long userId){
        UserResponse response=userService.updateUserById(userRequest,userId);
        return ResponseBuilder.ok (response,"User Updated");
    }
}


