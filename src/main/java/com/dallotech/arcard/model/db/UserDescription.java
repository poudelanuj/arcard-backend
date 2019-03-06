package com.dallotech.arcard.model.db;


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
    private Integer id;

    @Column(name="summary")
    private String summary;

    @Column(name="hobby")
    private String hobby;


    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

}
