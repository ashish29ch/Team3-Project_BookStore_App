package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.UserUpdateDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.LoginDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.userDTO.UserRequestDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.RoleNotValid;
import com.digitInsurance.bookStoreServicesApp.exception.customException.UserNotFoundException;
import com.digitInsurance.bookStoreServicesApp.exception.customException.UsernameAlreadyExistException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    public void registerUser(UserRequestDTO userRequestDTO) throws RoleNotValid, UsernameAlreadyExistException;

    public ResponseEntity<Map<String,String>> loginUser(LoginDTO loginDTO);

    public void updateUser(Long userId, UserUpdateDTO userUpdateDTO) throws UserNotFoundException;
}
