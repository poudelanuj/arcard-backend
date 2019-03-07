package com.dallotech.arcard.service;

import com.dallotech.arcard.model.db.Address;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.db.UserDescription;
import com.dallotech.arcard.model.dto.UserDescriptionDto;
import com.dallotech.arcard.model.dto.UserDto;
import com.dallotech.arcard.model.dto.UserEditRequestDto;
import com.dallotech.arcard.model.internal.LoggedUser;
import com.dallotech.arcard.payload.ApiResponse;
import com.dallotech.arcard.repository.AddressRepository;
import com.dallotech.arcard.repository.UserDescriptionRepository;
import com.dallotech.arcard.repository.UserRepository;
import com.dallotech.arcard.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserDescriptionService userDescriptionService;

    @Autowired
    AddressService addressService;

    @Autowired
    UserDescriptionRepository userDescriptionRepository;


    public ResponseEntity<?> getUserDtoFromUserPrincipal(UserPrincipal userPrincipal){
        Optional<User> userOptional= userRepository.findByEmail(userPrincipal.getEmail());
        if(userOptional.isPresent()){
            User user=userOptional.get();
            return ResponseEntity.ok(UserDto.getUserDtoFromUser(user));
        }
        return new ResponseEntity<>(new ApiResponse(false,"User not found"), HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> editInformation(LoggedUser loggedUser, UserEditRequestDto userEditRequestDto){


        User user=loggedUser.getUser();
        user.setFirstName(userEditRequestDto.getFirstName());
        user.setLastName(userEditRequestDto.getLastName());
        user.setFacebookLink(userEditRequestDto.getFacebookLink());
        user.setInstagramLink(userEditRequestDto.getInstagramLink());
        user.setTwitterLink(userEditRequestDto.getTwitterLink());
        user.setPhone(userEditRequestDto.getPhone());
        user.setLinkedinLink(userEditRequestDto.getLinkedinLink());
        UserDescription userDescription=user.getUserDescription();
        Address address=user.getAddress();

        if(userDescription==null){
            userDescription= UserDescription.getUserDescriptionFromDto(userEditRequestDto.getUserDescriptionDto());
        }else{
            userDescription=userDescriptionService.updateUserDescription(userDescription,userEditRequestDto.getUserDescriptionDto());
        }
        if(address==null){
            address=Address.getAddressFromDto(userEditRequestDto.getAddressDto());
        }else{
            address=addressService.updateAddress(address,userEditRequestDto.getAddressDto());
        }

        user.setUserDescription(userDescription);
        user.setAddress(address);
        user=userRepository.save(user);
        return ResponseEntity.ok(UserDto.getUserDtoFromUser(user));


    }



}
