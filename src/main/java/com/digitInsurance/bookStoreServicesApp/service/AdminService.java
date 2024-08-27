package com.digitInsurance.bookStoreServicesApp.service;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.RequestDTO;
import com.digitInsurance.bookStoreServicesApp.model.Users;

public interface AdminService {
   public void registerAdmin(RequestDTO requestDTO);

   public Users loginAdmin(String userName, String password);
}
