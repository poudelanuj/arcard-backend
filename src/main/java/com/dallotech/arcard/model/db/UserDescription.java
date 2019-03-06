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


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="summary")
    private String summary;

    @Column(name="hobby")
    private String hobby;


    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public static UserDescription getUserDescriptionFromDto(UserDescriptionDto userDescriptionDto){
        UserDescription userDescription=new UserDescription();
        userDescription.setSummary(userDescriptionDto.getSummary());
        userDescription.setHobby(userDescription.getHobby());
        return userDescription;
    }

}
