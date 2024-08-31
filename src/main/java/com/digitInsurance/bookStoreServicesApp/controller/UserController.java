package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.UserUpdateDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.LoginDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.userDTO.UserRequestDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.RoleNotValid;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.UserNotFoundException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.UsernameAlreadyExistException;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.UserService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bookstore/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws RoleNotValid, UsernameAlreadyExistException {
        userService.registerUser(userRequestDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            ResponseEntity<String> token = userService.loginUser(loginDTO);
            return token;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


    @PutMapping("/update/user")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @RequestHeader("Authorization") String token) throws UserNotFoundException, TokenNotValidException, TokenNotValidException {
        Long userId = JWTToken.getUserIdFromToken(token);
        System.out.println("User ID from token: " + userId);
        userService.updateUser(userId, userUpdateDTO);
        System.out.println("User details updated successfully");
        return ResponseEntity.ok("User details updated successfully");
    }
}
