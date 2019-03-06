package com.dallotech.arcard.service;

import com.dallotech.arcard.exception.UserNotFoundException;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.dto.QrDataRequestDto;
import com.dallotech.arcard.model.dto.UserDto;
import com.dallotech.arcard.repository.UserRepository;
import com.dallotech.arcard.utils.EncryptionDecryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

@Service("qrService")
public class QrService {

    @Autowired
    UserRepository userRepository;



    public ResponseEntity<?> getUserInformationFromQrData(QrDataRequestDto qrDataRequestDto) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String uuid= EncryptionDecryption.decrypt(qrDataRequestDto.getQrData());
        Optional<User> userOptional=userRepository.findById(UUID.fromString(uuid));
        if(userOptional.isPresent()){
            UserDto userDto=UserDto.getUserDtoFromUser(userOptional.get());
            return ResponseEntity.ok(userDto);
        }
        return new ResponseEntity(new UserNotFoundException(), HttpStatus.NOT_FOUND);

    }

}
