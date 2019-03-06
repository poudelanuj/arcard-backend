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
    @JsonProperty("name")
    private String name;
    @JsonProperty("facebook_link")
    private String facebookLink;
    @JsonProperty("instagram_link")
    private String instagramLink;
    @JsonProperty("google_link")
    private String googleLink;
    @JsonProperty("youtube_link")
    private String youtubeLink;
    @JsonProperty("linkedin_link")
    private String linkedinLink;
    @JsonProperty("user_description")
    private UserDescriptionDto userDescriptionDto;

    public static UserDto getUserDtoFromUser(User user){
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setFacebookLink(user.getFacebookLink());
        userDto.setInstagramLink(user.getInstagramLink());
        userDto.setGoogleLink(user.getGoogleLink());
        userDto.setYoutubeLink(user.getYoutubeLink());
        userDto.setLinkedinLink(user.getLinkedinLink());
        userDto.setUserDescriptionDto(UserDescriptionDto.getUserDescriptionDto(user.getUserDescription()));
        return userDto;
    }

}
