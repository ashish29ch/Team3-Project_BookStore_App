package com.digitInsurance.bookStoreServicesApp.controller;


import com.digitInsurance.bookStoreServicesApp.dto.requestdto.addressDTO.AddressDTO;
import com.digitInsurance.bookStoreServicesApp.exception.customException.TokenNotValidException;
import com.digitInsurance.bookStoreServicesApp.model.Address;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.AddressService;
import com.digitInsurance.bookStoreServicesApp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
@Validated
public class AddressController {
    @Autowired
    private AddressService addressService;
    @PostMapping("/add/address")
    public ResponseEntity<?> addAddress(@RequestHeader("Authorization") String token,@RequestBody AddressDTO addressDTO) throws TokenNotValidException {
        Long userId = JWTToken.getUserIdFromToken(token);
        Address address = addressService.addAddress(userId, addressDTO);
        return ResponseEntity.ok("Address added successfully");
    }
    @PutMapping("/update_address/{address_id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long address_id,@RequestHeader("Authorization") String token, @RequestBody AddressDTO addressDTO) throws TokenNotValidException {
        Long userId = JWTToken.getUserIdFromToken(token);
        Address updatedAddress = addressService.updateAddress(address_id,userId, addressDTO);
        return ResponseEntity.ok("Address updated successfully");
    }

    @GetMapping("/view_address")
    public ResponseEntity<List<AddressDTO>> viewAddress(@RequestHeader("Authorization") String token) throws TokenNotValidException {
        Long userId = JWTToken.getUserIdFromToken(token);
        List<Address> addresses = addressService.getAddresses(userId);

        List<AddressDTO> addressDTOs = addresses.stream().map(address -> {
            AddressDTO addressDTO = new AddressDTO();
            //addressDTO.setId(address.getId());
            addressDTO.setFullName(address.getFullName());
            addressDTO.setMobileNumber(address.getMobileNumber());
            addressDTO.setAddressLine1(address.getAddressLine1());
            addressDTO.setAddressLine2(address.getAddressLine2());
            addressDTO.setLandmark(address.getLandmark());
            addressDTO.setPincode(address.getPincode());
            addressDTO.setCity(address.getCity());
            addressDTO.setState(address.getState());
            return addressDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(addressDTOs);
    }
    @DeleteMapping("/delete/{address_id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long address_id, @RequestHeader("Authorization") String token) throws TokenNotValidException {
        Long userId = JWTToken.getUserIdFromToken(token);
        addressService.deleteAddress(address_id, userId);
        return ResponseEntity.ok("Address deleted successfully");
    }

}
