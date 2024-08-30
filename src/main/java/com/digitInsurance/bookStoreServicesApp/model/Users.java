package com.digitInsurance.bookStoreServicesApp.model;


import com.digitInsurance.bookStoreServicesApp.dto.requestdto.adminDTO.RequestDTO;
import com.digitInsurance.bookStoreServicesApp.dto.requestdto.userDTO.UserRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @JsonIgnore
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private RoleName role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Address> address=new ArrayList<>();

    public Users(RequestDTO requestDTO){

        this.username = requestDTO.getUsername();
        this.email = requestDTO.getEmail();
        this.password = requestDTO.getPassword();
        this.role = RoleName.valueOf(requestDTO.getRole());
    }

    public Users(UserRequestDTO userRequestDTO) {
        this.username = userRequestDTO.getUsername();
        this.email = userRequestDTO.getEmail();
        this.password = userRequestDTO.getPassword();
        this.role = RoleName.valueOf(userRequestDTO.getRole());
    }
}
