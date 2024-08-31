package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.LoginDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.RequestDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.RoleNotValid;
import com.digitInsurance.bookStoreServicesApp.exception.customException.UsernameAlreadyExistException;
import com.digitInsurance.bookStoreServicesApp.model.RoleName;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import com.digitInsurance.bookStoreServicesApp.repo.UserRepository;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.AdminService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    public UserRepository userRepository;

    public void registerAdmin(RequestDTO requestDTO) throws RoleNotValid, UsernameAlreadyExistException {
        Optional<Users> isAdmin = userRepository.findByEmail(requestDTO.getEmail());
        Optional<Users> isAdminUsername = userRepository.findByUsername(requestDTO.getUsername());

        if (isAdmin.isEmpty() && isAdminUsername.isEmpty()) {
            String hashPassword = BCrypt.hashpw(requestDTO.getPassword(), BCrypt.gensalt());
            requestDTO.setPassword(hashPassword);

            if (requestDTO.getRole().equalsIgnoreCase("admin")) {
                requestDTO.setRole(String.valueOf(RoleName.ROLE_ADMIN));
            } else {
                throw new RoleNotValid("Role Not Valid");
            }

            Users user = new Users(requestDTO);
            userRepository.save(user);
        } else {
            throw new UsernameAlreadyExistException("Username Already Registered");
        }
    }

    public ResponseEntity<Map<String, String>> loginAdmin(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            Optional<Users> userFound = userRepository.findByEmail(loginDTO.getEmail());

            if (userFound.isEmpty()) {
                return new ResponseEntity<>(Map.of("error", "Admin With Email Not Found"), HttpStatus.NOT_FOUND);
            }

            String userPassword = userFound.get().getPassword();
            boolean checkPassword = BCrypt.checkpw(loginDTO.getPassword(), userPassword);
            if (checkPassword) {
                String token = JWTToken.getToken(String.valueOf(userFound.get().getRole()), userFound.get().getId());
                return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Map.of("error", "Invalid Password"), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }


}
