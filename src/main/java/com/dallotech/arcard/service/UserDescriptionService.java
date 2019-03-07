package com.dallotech.arcard.service;

import com.dallotech.arcard.model.db.UserDescription;
import com.dallotech.arcard.model.dto.UserDescriptionDto;
import org.springframework.stereotype.Service;

@Service("userDescriptionService")
public class UserDescriptionService {

    public UserDescription updateUserDescription(UserDescription userDescription, UserDescriptionDto userDescriptionDto){
        userDescription.setHobby(userDescriptionDto.getHobby());
        userDescription.setSummary(userDescriptionDto.getSummary());
        userDescription.setProfessionalDetails(userDescriptionDto.getProfessionalDetails());
        userDescription.setSkills(userDescriptionDto.getSkills());
        return userDescription;
    }

}
