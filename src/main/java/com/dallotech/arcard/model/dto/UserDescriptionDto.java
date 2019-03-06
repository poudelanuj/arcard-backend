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
public class UserDescriptionDto {

    @JsonProperty("summary")
    private String summary;
    @JsonProperty("hobby")
    private String hobby;

    public static UserDescriptionDto getUserDescriptionDto(UserDescription userDescription){
        UserDescriptionDto userDescriptionDto=new UserDescriptionDto();
        userDescriptionDto.setHobby(userDescription.getHobby());
        userDescriptionDto.setSummary(userDescription.getSummary());
        return userDescriptionDto;
    }

}
