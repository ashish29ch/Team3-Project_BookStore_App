package com.digitInsurance.bookStoreServicesApp.controller;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.LoginDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.RequestDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.RoleNotValid;
import com.digitInsurance.bookStoreServicesApp.exception.customException.UsernameAlreadyExistException;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/bookstore_user/admin")
@Validated
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RequestDTO requestDTO) throws RoleNotValid, UsernameAlreadyExistException {
        adminService.registerAdmin(requestDTO);
        return ResponseEntity.ok("Admin registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            ResponseEntity<String> token = adminService.loginAdmin(loginDTO);
            return token;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
