package com.digitInsurance.bookStoreServicesApp.service;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.RequestDTO;
import com.digitInsurance.bookStoreServicesApp.exception.RoleNotValid;
import com.digitInsurance.bookStoreServicesApp.exception.UsernameAlreadyExistException;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import org.springframework.stereotype.Component;


public interface AdminService {
   public void registerAdmin(RequestDTO requestDTO) throws RoleNotValid, UsernameAlreadyExistException;

   public Users loginAdmin(String userName, String password);
}
