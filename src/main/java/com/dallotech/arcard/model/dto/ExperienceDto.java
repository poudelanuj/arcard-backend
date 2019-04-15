package com.dallotech.arcard.model.dto;

import com.dallotech.arcard.model.db.Experience;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("job_title")
    private String jobTitle;
    @JsonProperty("institution")
    private String institution;
    @JsonProperty("location")
    private String location;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;


    public static ExperienceDto getExperienceDto(Experience experience){
        ExperienceDto experienceDto=new ExperienceDto();
        experienceDto.setId(experience.getId());
        experienceDto.setJobTitle(experience.getJobTitle());
        experienceDto.setInstitution(experience.getInstitution());
        experienceDto.setLocation(experience.getLocation());
        experienceDto.setStartTime(experience.getStartTime());
        experienceDto.setEndTime(experience.getEndTime());
        return experienceDto;

    }
}
