package com.dallotech.arcard.service;

import com.dallotech.arcard.model.db.Address;
import com.dallotech.arcard.model.db.Education;
import com.dallotech.arcard.model.db.Experience;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.dto.EducationDto;
import com.dallotech.arcard.model.dto.ExperienceDto;
import com.dallotech.arcard.model.dto.UserDto;
import com.dallotech.arcard.model.dto.UserEditRequestDto;
import com.dallotech.arcard.model.internal.LoggedUser;
import com.dallotech.arcard.payload.ApiResponse;
import com.dallotech.arcard.repository.AddressRepository;
import com.dallotech.arcard.repository.EducationRepository;
import com.dallotech.arcard.repository.ExperienceRepository;
import com.dallotech.arcard.repository.UserRepository;
import com.dallotech.arcard.security.AddressService;
import com.dallotech.arcard.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userService")
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EducationService educationService;

    @Autowired
    ExperienceService experienceService;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    AddressRepository addressRepository;

    public ResponseEntity<?> getUserDtoFromUserPrincipal(UserPrincipal userPrincipal) {
        Optional<User> userOptional = userRepository.findByEmail(userPrincipal.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            return ResponseEntity.ok(getCompleteUserDetail(user));
        }
        return new ResponseEntity<>(new ApiResponse(false, "User not found"), HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<?> editInformation(LoggedUser loggedUser, UserEditRequestDto userEditRequestDto) {


        User user = loggedUser.getUser();
        user.setFirstName(userEditRequestDto.getFirstName());
        user.setMiddleName(userEditRequestDto.getMiddleName());
        user.setLastName(userEditRequestDto.getLastName());
        user.setPhone(userEditRequestDto.getPhone());
        user.setAboutMe(userEditRequestDto.getAboutMe());
        user.setSkills(userEditRequestDto.getSkills());
        user.setProjects(userEditRequestDto.getProjects());
        user.setActivities(userEditRequestDto.getActivities());
        user.setProtfolioLink(userEditRequestDto.getProtfolioLink());
        user.setFacebookLink(userEditRequestDto.getFacebookLink());
        user.setInstagramLink(userEditRequestDto.getInstagramLink());
        user.setTwitterLink(userEditRequestDto.getTwitterLink());
        user.setLinkedinLink(userEditRequestDto.getLinkedinLink());

        Address address=addressService.editAddress(userEditRequestDto, user);


        if (!userEditRequestDto.getExperienceDtoList().isEmpty()) {
            experienceService.addExperience(userEditRequestDto.getExperienceDtoList(), user);
        }

        if (!userEditRequestDto.getEducationDtoList().isEmpty()) {
            educationService.addEducation(userEditRequestDto.getEducationDtoList(), user);
        }
        user = userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "Information Successfully Added"));


    }

    public UserDto getCompleteUserDetail(User user) {
        Optional<List<Education>> optionalEducationList = educationRepository.findByUser_Id(user.getId());
        Optional<List<Experience>> optionalExperienceList = experienceRepository.findByUser_Id(user.getId());
        Optional<Address> addressOptional = addressRepository.findByUser_Id(user.getId());

        List < EducationDto > educationDtoList = new ArrayList<>();
        List<ExperienceDto> experienceDtoList = new ArrayList<>();
        if (optionalEducationList.isPresent()) {
            educationDtoList = optionalEducationList.get().stream().map(EducationDto::getEducationDto).collect(Collectors.toList());

        }

        if (optionalExperienceList.isPresent()) {

            experienceDtoList = optionalExperienceList.get().stream().map(ExperienceDto::getExperienceDto).collect(Collectors.toList());

        }

        return UserDto.getUserDtoFromUser(user, educationDtoList, experienceDtoList, addressOptional.orElseGet(Address::new));


    }


}
