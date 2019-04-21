package com.dallotech.arcard.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEditRequestDto {

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
    @JsonProperty("city")
    private String city;
    @JsonProperty("street_address")
    private String streetAddress;
    @JsonProperty("state")
    private String state;
    @JsonProperty("country")
    private String country;



}
