package com.dallotech.arcard.model.db;

import com.dallotech.arcard.model.dto.EducationDto;
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
@Table(name = "education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name = "degree")
    private String degree;

    @Column(name = "institution")
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

    public static Education getEducationFromDto(EducationDto educationDto, User user){
        Education education=new Education();
        education.setDegree(educationDto.getDegree());
        education.setInstitution(educationDto.getInstitution());
        education.setLocation(educationDto.getLocation());
        education.setStartTime(educationDto.getStartTime());
        education.setEndTime(educationDto.getEndTime());
        education.setUser(user);
        return education;
    }

}
