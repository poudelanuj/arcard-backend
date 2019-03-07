package com.dallotech.arcard.model.dto;

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
    @JsonProperty("professional_details")
    private String professionalDetails;
    @JsonProperty("skills")
    private String skills;

    public static UserDescriptionDto getUserDescriptionDto(UserDescription userDescription){
        UserDescriptionDto userDescriptionDto=new UserDescriptionDto();
        userDescriptionDto.setHobby(userDescription.getHobby());
        userDescriptionDto.setSummary(userDescription.getSummary());
        userDescriptionDto.setProfessionalDetails(userDescription.getProfessionalDetails());
        userDescriptionDto.setSkills(userDescription.getSkills());
        return userDescriptionDto;
    }

}
