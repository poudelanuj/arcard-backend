package com.dallotech.arcard.model.db;


import com.dallotech.arcard.model.dto.UserDescriptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_description")
public class UserDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="summary")
    private String summary;

    @Column(name="hobby")
    private String hobby;

    @Column(name = "professional_details")
    private String professionalDetails;

    @Column(name = "skills")
    private String skills;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public static UserDescription getUserDescriptionFromDto(UserDescriptionDto userDescriptionDto){
        UserDescription userDescription=new UserDescription();
        userDescription.setSummary(userDescriptionDto.getSummary());
        userDescription.setHobby(userDescriptionDto.getHobby());
        return userDescription;
    }

}
