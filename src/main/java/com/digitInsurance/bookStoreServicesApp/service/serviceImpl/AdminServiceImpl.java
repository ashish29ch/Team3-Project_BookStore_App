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

    public ResponseEntity<String> loginAdmin(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            Optional<Users> userFound = userRepository.findByUsername(loginDTO.getUsername());

            if (userFound.isEmpty()) {
                return new ResponseEntity<>("Admin With Username Not Found", HttpStatus.NOT_FOUND);
            }

            String userPassword = userFound.get().getPassword();
            boolean checkPassword = BCrypt.checkpw(loginDTO.getPassword(), userPassword);
            String token = null;
            if(checkPassword){
                token = JWTToken.getToken(String.valueOf(userFound.get().getRole()));
            }
            else {
                return new ResponseEntity<>("Invalid Password", HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(token,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
