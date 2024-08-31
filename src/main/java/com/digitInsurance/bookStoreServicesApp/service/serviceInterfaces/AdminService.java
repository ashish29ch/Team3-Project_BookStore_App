package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.LoginDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.RequestDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.RoleNotValid;
import com.digitInsurance.bookStoreServicesApp.exception.customException.UsernameAlreadyExistException;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public interface AdminService {
   public void registerAdmin(RequestDTO requestDTO) throws RoleNotValid, UsernameAlreadyExistException;

   public ResponseEntity<Map<String, String>> loginAdmin(LoginDTO loginDTO);
}
