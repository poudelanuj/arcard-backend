package com.dallotech.arcard.service;

import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.db.UserDescription;
import com.dallotech.arcard.model.dto.UserDescriptionDto;
import com.dallotech.arcard.model.dto.UserDto;
import com.dallotech.arcard.model.internal.LoggedUser;
import com.dallotech.arcard.payload.ApiResponse;
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
    UserDescriptionRepository userDescriptionRepository;

    public ResponseEntity<?> getUserDtoFromUserPrincipal(UserPrincipal userPrincipal){
        Optional<User> userOptional= userRepository.findByEmail(userPrincipal.getEmail());
        if(userOptional.isPresent()){
            User user=userOptional.get();
            return ResponseEntity.ok(UserDto.getUserDtoFromUser(user));
        }
        return new ResponseEntity<>(new ApiResponse(false,"User not found"), HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> editInformation(LoggedUser loggedUser,UserDto userDto){


        User user=loggedUser.getUser();
        user.setFacebookLink(userDto.getFacebookLink());
        user.setInstagramLink(userDto.getInstagramLink());
        user.setGoogleLink(userDto.getGoogleLink());
        user.setYoutubeLink(userDto.getYoutubeLink());
        user.setLinkedinLink(userDto.getLinkedinLink());

        UserDescription userDescription= UserDescription.getUserDescriptionFromDto(userDto.getUserDescriptionDto());
        userDescription=userDescriptionRepository.save(userDescription);

        user.setUserDescription(userDescription);
        user=userRepository.save(user);
        return ResponseEntity.ok(user);


    }



}
