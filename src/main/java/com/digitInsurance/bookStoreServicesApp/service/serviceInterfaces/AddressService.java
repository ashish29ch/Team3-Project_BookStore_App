package com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.addressDTO.AddressDTO;
import com.digitInsurance.bookStoreServicesApp.model.Address;

import java.util.List;

public interface AddressService {
    public Address addAddress(Long userId, AddressDTO addressDTO);

    public List<Address> getAddresses(Long userId);
    public Address updateAddress(Long address_id,Long userId, AddressDTO addressDTO);

    public void deleteAddress(Long addressId, Long userId);
}
