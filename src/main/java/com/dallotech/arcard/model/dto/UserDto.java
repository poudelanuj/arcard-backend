package com.dallotech.arcard.model.dto;

import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.db.UserDescription;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("facebook_link")
    private String facebookLink;
    @JsonProperty("instagram_link")
    private String instagramLink;
    @JsonProperty("twitter_link")
    private String twitterLink;
    @JsonProperty("linkedin_link")
    private String linkedinLink;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("user_description")
    private UserDescriptionDto userDescriptionDto;
    @JsonProperty("address")
    private AddressDto addressDto;
    public static UserDto getUserDtoFromUser(User user){
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setFacebookLink(user.getFacebookLink());
        userDto.setInstagramLink(user.getInstagramLink());
        userDto.setTwitterLink(user.getTwitterLink());
        userDto.setPhone(user.getPhone());
        userDto.setLinkedinLink(user.getLinkedinLink());
        if(user.getUserDescription()!=null){
            userDto.setUserDescriptionDto(UserDescriptionDto.getUserDescriptionDto(user.getUserDescription()));
        }
        if(user.getAddress()!=null){
            userDto.setAddressDto(AddressDto.getAddressDtoFromAddress(user.getAddress()));
        }
        return userDto;
    }

}
