package com.dallotech.arcard.service;

import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.dto.UserDto;
import com.dallotech.arcard.payload.ApiResponse;
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

    public ResponseEntity<?> getUserDtoFromUserPrincipal(UserPrincipal userPrincipal){
        Optional<User> userOptional= userRepository.findByEmail(userPrincipal.getEmail());
        if(userOptional.isPresent()){
            User user=userOptional.get();
            return ResponseEntity.ok(UserDto.getUserDtoFromUser(user));
        }
        return new ResponseEntity<>(new ApiResponse(false,"User not found"), HttpStatus.NOT_FOUND);

    }

}
