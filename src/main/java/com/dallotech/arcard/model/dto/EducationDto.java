package com.dallotech.arcard.model.dto;

import com.dallotech.arcard.model.db.Education;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducationDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("degree")
    private String degree;
    @JsonProperty("institution")
    private String institution;
    @JsonProperty("location")
    private String location;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;

    public static EducationDto getEducationDto(Education education){
        EducationDto educationDto=new EducationDto();
        educationDto.setId(education.getId());
        educationDto.setDegree(education.getDegree());
        educationDto.setInstitution(education.getInstitution());
        educationDto.setLocation(education.getLocation());
        educationDto.setStartTime(education.getStartTime());
        educationDto.setEndTime(education.getEndTime());
        return educationDto;
    }


}
