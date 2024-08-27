package com.digitInsurance.bookStoreServicesApp.service;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.RequestDTO;
import com.digitInsurance.bookStoreServicesApp.model.Admin;
import com.digitInsurance.bookStoreServicesApp.model.Role;
import com.digitInsurance.bookStoreServicesApp.model.RoleName;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import com.digitInsurance.bookStoreServicesApp.repo.AdminRepository;
import com.digitInsurance.bookStoreServicesApp.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RequestDTO requestDTO;

    @Autowired
    //private PasswordEncoder passwordEncoder;

    public void registerAdmin(RequestDTO requestDTO) {
        // Check if the admin already exists
//        if (adminRepository.findByUsername(requestDTO.getUsername()).isPresent()) {
//            throw new CustomException("Admin already exists");
//        }

        // Validate the password
//        if (!isValidPassword(requestDTO.getPassword())) {
//            throw new CustomException("Invalid password");
//        }

        // Create a new Admin entity
        Admin admin = new Admin();
        admin.setUsername(requestDTO.getUsername());
        //admin.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        admin.setEmail(requestDTO.getEmail());

        // Fetch the ROLE_ADMIN from the roles table
        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN);

        // Assign the ROLE_ADMIN to the new admin
        admin.getRoles().add(adminRole);

        // Save the new admin to the database
        adminRepository.save(admin);

    }

    @Override
    public Users loginAdmin(String userName, String password) {
        return null;
    }
}
