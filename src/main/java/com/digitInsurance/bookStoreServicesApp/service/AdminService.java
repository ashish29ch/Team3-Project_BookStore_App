package com.digitInsurance.bookStoreServicesApp.service;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.LoginDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.RequestDTO;
import com.digitInsurance.bookStoreServicesApp.exception.RoleNotValid;
import com.digitInsurance.bookStoreServicesApp.exception.UsernameAlreadyExistException;
import org.springframework.http.ResponseEntity;


public interface AdminService {
   public void registerAdmin(RequestDTO requestDTO) throws RoleNotValid, UsernameAlreadyExistException;

   public ResponseEntity<String> loginAdmin(LoginDTO loginDTO);
}
