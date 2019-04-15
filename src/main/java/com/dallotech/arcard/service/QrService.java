package com.dallotech.arcard.service;

import com.dallotech.arcard.exception.UserNotFoundException;
import com.dallotech.arcard.model.db.Education;
import com.dallotech.arcard.model.db.Experience;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.dto.EducationDto;
import com.dallotech.arcard.model.dto.ExperienceDto;
import com.dallotech.arcard.model.dto.QrDataRequestDto;
import com.dallotech.arcard.model.dto.UserDto;
import com.dallotech.arcard.repository.EducationRepository;
import com.dallotech.arcard.repository.ExperienceRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("qrService")
public class QrService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    UserService userService;



    public ResponseEntity<?> getUserInformationFromQrData(QrDataRequestDto qrDataRequestDto) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Long id= qrDataRequestDto.getQrData();
        Optional<User> userOptional=userRepository.findById(id);
        if(userOptional.isPresent()){
            return ResponseEntity.ok(userService.getCompleteUserDetail(userOptional.get()));
        }
        return new ResponseEntity(new UserNotFoundException(), HttpStatus.NOT_FOUND);

    }

}
