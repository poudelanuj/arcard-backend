package com.dallotech.arcard.security;

import com.dallotech.arcard.model.db.Address;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.dto.UserEditRequestDto;
import com.dallotech.arcard.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;


    public Address  editAddress(UserEditRequestDto userEditRequestDto, User user){
        Optional<Address> addressOptional=addressRepository.findByUser_Id(user.getId());
        if(addressOptional.isPresent()){
            return saveAddress(addressOptional.get(),userEditRequestDto, user);
        }else{
            return saveAddress(new Address(),userEditRequestDto, user);
        }
    }

    private Address saveAddress(Address address, UserEditRequestDto userEditRequestDto, User user){
        address.setCity(userEditRequestDto.getCity());
        address.setStreetAddress(userEditRequestDto.getStreetAddress());
        address.setState(userEditRequestDto.getState());
        address.setCountry(userEditRequestDto.getCountry());
        address.setUser(user);
        return addressRepository.save(address);
    }

}
