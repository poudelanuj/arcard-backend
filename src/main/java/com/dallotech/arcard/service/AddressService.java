package com.dallotech.arcard.service;

import com.dallotech.arcard.model.db.Address;
import com.dallotech.arcard.model.dto.AddressDto;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressService {

    public Address updateAddress(Address address,AddressDto addressDto){
        address.setCity(addressDto.getCity());
        address.setStreetAddress(addressDto.getStreetAddress());
        address.setState(addressDto.getState());
        address.setPinCode(addressDto.getPinCode());
        address.setCountry(addressDto.getCountry());
        return address;
    }

}
