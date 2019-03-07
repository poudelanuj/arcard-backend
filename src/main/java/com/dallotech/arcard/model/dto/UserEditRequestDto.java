package com.dallotech.arcard.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEditRequestDto {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("facebook_link")
    private String facebookLink;
    @JsonProperty("instagram_link")
    private String instagramLink;
    @JsonProperty("twitter_link")
    private String twitterLink;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("linkedin_link")
    private String linkedinLink;
    @JsonProperty("user_description")
    private UserDescriptionDto userDescriptionDto;
    @JsonProperty("address")
    private AddressDto addressDto;


}
