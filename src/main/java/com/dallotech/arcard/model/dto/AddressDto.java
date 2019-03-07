package com.dallotech.arcard.model.dto;

import com.dallotech.arcard.model.db.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDto {

    @JsonProperty("city")
    private String city;
    @JsonProperty("street_address")
    private String streetAddress;
    @JsonProperty("state")
    private String state;
    @JsonProperty("pin_code")
    private String pinCode;
    @JsonProperty("country")
    private String country;

    public static AddressDto getAddressDtoFromAddress(Address address){
        AddressDto addressDto=new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setStreetAddress(address.getStreetAddress());
        addressDto.setState(address.getState());
        addressDto.setPinCode(address.getPinCode());
        addressDto.setCountry(address.getCountry());
        return addressDto;
    }


}
