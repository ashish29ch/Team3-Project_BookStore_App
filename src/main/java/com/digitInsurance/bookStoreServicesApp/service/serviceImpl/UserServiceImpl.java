package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.LoginDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.userDTO.UserRequestDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.RoleNotValid;
import com.digitInsurance.bookStoreServicesApp.exception.customException.UsernameAlreadyExistException;
import com.digitInsurance.bookStoreServicesApp.model.RoleName;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import com.digitInsurance.bookStoreServicesApp.repo.UserRepository;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.UserService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public void registerUser(UserRequestDTO userRequestDTO) throws RoleNotValid, UsernameAlreadyExistException {

        Optional<Users> isUserEmail = userRepository.findByEmail(userRequestDTO.getEmail());
        Optional<Users> isUserName = userRepository.findByUsername(userRequestDTO.getUsername());

        if(isUserEmail.isEmpty() && isUserName.isEmpty()){

            String hashPassword = BCrypt.hashpw(userRequestDTO.getPassword(), BCrypt.gensalt());
            userRequestDTO.setPassword(hashPassword);

            if(userRequestDTO.getRole().equalsIgnoreCase("user")){
                userRequestDTO.setRole(String.valueOf(RoleName.ROLE_USER));
            }
            else {
                throw new RoleNotValid("Role Not Valid");
            }

            Users user = new Users(userRequestDTO);

            userRepository.save(user);
        }
        else {
            throw new UsernameAlreadyExistException("Username Already Registered ");
        }
    }


    public ResponseEntity<String> loginUser(LoginDTO loginDTO) {
        try {
            Optional<Users> userFound = userRepository.findByUsername(loginDTO.getUsername());

            if (userFound.isEmpty()) {
                return new ResponseEntity<>("User With Username Not Found", HttpStatus.NOT_FOUND);
            }

            String userPassword = userFound.get().getPassword();
            boolean checkPassword = BCrypt.checkpw(loginDTO.getPassword(), userPassword);
            String token = null;
            if(checkPassword){
                token = JWTToken.getToken(String.valueOf(userFound.get().getRole()),userFound.get().getId());
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
