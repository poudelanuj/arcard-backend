package com.dallotech.arcard.model.db;


import com.dallotech.arcard.model.dto.ExperienceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name="institution")
    private String institution;

    @Column(name = "location")
    private String location;

    @Column(name="start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public static Experience getExperienceFromDto(ExperienceDto experienceDto,User user){
        Experience experience=new Experience();
        experience.setJobTitle(experienceDto.getJobTitle());
        experience.setInstitution(experienceDto.getInstitution());
        experience.setLocation(experienceDto.getLocation());
        experience.setStartTime(experienceDto.getStartTime());
        experience.setEndTime(experienceDto.getEndTime());
        experience.setUser(user);
        return experience;
    }


}
