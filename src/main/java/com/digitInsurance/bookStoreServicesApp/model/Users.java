package com.digitInsurance.bookStoreServicesApp.model;


import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.RequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleName role;

    public Users(RequestDTO requestDTO){

        this.username = requestDTO.getUsername();
        this.email = requestDTO.getEmail();
        this.password = requestDTO.getPassword();
        this.role = RoleName.valueOf(requestDTO.getRole());
    }
}
