package com.digitInsurance.bookStoreServicesApp.service.serviceImpl;

import com.digitInsurance.bookStoreServicesApp.dto.requestdto.addressDTO.AddressDTO;
import com.digitInsurance.bookStoreServicesApp.model.Address;
import com.digitInsurance.bookStoreServicesApp.model.Users;
import com.digitInsurance.bookStoreServicesApp.repo.AddressRepository;
import com.digitInsurance.bookStoreServicesApp.repo.UserRepository;
import com.digitInsurance.bookStoreServicesApp.service.serviceInterfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address addAddress(Long userId, AddressDTO addressDTO) {
        Users user = userRepository.findById(userId).get();
        Address address = new Address();
        address.setFullName(addressDTO.getFullName());
        address.setMobileNumber(addressDTO.getMobileNumber());
        address.setAddressLine1(addressDTO.getAddressLine1());
        address.setAddressLine2(addressDTO.getAddressLine2());
        address.setLandmark(addressDTO.getLandmark());
        address.setPincode(addressDTO.getPincode());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddresses(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public Address updateAddress(Long address_id, Long userId, AddressDTO addressDTO) {
        Users user = userRepository.findById(userId).get();
        Address address = addressRepository.findById(address_id).orElseThrow(() -> new RuntimeException("Address not found"));
        address.setFullName(addressDTO.getFullName());
        address.setMobileNumber(addressDTO.getMobileNumber());
        address.setAddressLine1(addressDTO.getAddressLine1());
        address.setAddressLine2(addressDTO.getAddressLine2());
        address.setLandmark(addressDTO.getLandmark());
        address.setPincode(addressDTO.getPincode());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long addressId, Long userId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new RuntimeException("Address not found"));
        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("Address does not belong to the user");
        }
        addressRepository.delete(address);
    }
}
