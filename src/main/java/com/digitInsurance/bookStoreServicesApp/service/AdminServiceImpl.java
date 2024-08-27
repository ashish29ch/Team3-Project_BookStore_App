package com.digitInsurance.bookStoreServicesApp.service;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.LoginDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.RequestDTO;
import com.digitInsurance.bookStoreServicesApp.exception.RoleNotValid;
import com.digitInsurance.bookStoreServicesApp.exception.UsernameAlreadyExistException;
import com.digitInsurance.bookStoreServicesApp.model.RoleName;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import com.digitInsurance.bookStoreServicesApp.repo.UserRepository;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    public UserRepository userRepository;

    public void registerAdmin(RequestDTO requestDTO) throws RoleNotValid, UsernameAlreadyExistException {

        Optional<Users> isAdmin = userRepository.findByEmail(requestDTO.getEmail());

        if(isAdmin.isEmpty()){

            String hashPassword = BCrypt.hashpw(requestDTO.getPassword(), BCrypt.gensalt());
            requestDTO.setPassword(hashPassword);

            if(requestDTO.getRole().equalsIgnoreCase("admin")){
                requestDTO.setRole(String.valueOf(RoleName.ROLE_ADMIN));
            }
            else {
                throw new RoleNotValid("Role Not Valid");
            }

            Users user = new Users(requestDTO);

            userRepository.save(user);
        }
        else {
            throw new UsernameAlreadyExistException("Username Already Registered ");
        }

    }
    
}
