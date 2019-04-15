package com.dallotech.arcard.model.dto;

import com.dallotech.arcard.model.db.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    @JsonProperty("email")
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("about_me")
    private String aboutMe;
    @JsonProperty("skills")
    private List<String> skills;
    @JsonProperty("education")
    private List<EducationDto> educationDtoList;
    @JsonProperty("experience")
    private List<ExperienceDto> experienceDtoList;
    @JsonProperty("projects")
    private List<String> projects;
    @JsonProperty("activities")
    private List<String> activities;
    @JsonProperty("portfolio_link")
    private String protfolioLink;
    @JsonProperty("facebook_link")
    private String facebookLink;
    @JsonProperty("instagram_link")
    private String instagramLink;
    @JsonProperty("twitter_link")
    private String twitterLink;
    @JsonProperty("linkedin_link")
    private String linkedinLink;

    public static UserDto getUserDtoFromUser(User user, List<EducationDto> educationDtoList, List<ExperienceDto> experienceDtoList){
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setAboutMe(user.getAboutMe());
        userDto.setSkills(user.getSkills());
        userDto.setEducationDtoList(educationDtoList);
        userDto.setExperienceDtoList(experienceDtoList);
        userDto.setProjects(user.getProjects());
        userDto.setActivities(user.getActivities());
        userDto.setProtfolioLink(user.getProtfolioLink());
        userDto.setFacebookLink(user.getFacebookLink());
        userDto.setInstagramLink(user.getInstagramLink());
        userDto.setTwitterLink(user.getTwitterLink());
        userDto.setLinkedinLink(user.getLinkedinLink());

        return userDto;
    }

}
