package com.dallotech.arcard.model.db;

import com.dallotech.arcard.model.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "state")
    private String state;

    @Column(name="pin_code")
    private String pinCode;

    @Column(name = "country")
    private String country;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;


    public static Address getAddressFromDto(AddressDto addressDto){
        Address address=new Address();
        address.setCity(addressDto.getCity());
        address.setStreetAddress(addressDto.getStreetAddress());
        address.setState(addressDto.getState());
        address.setPinCode(addressDto.getPinCode());
        address.setCountry(addressDto.getCountry());
        return address;
    }


}
