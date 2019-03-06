package com.dallotech.arcard.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")


public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id_user",updatable = false,columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name="email",unique = true)
    String email;

    @Column(name="password")
    String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();

    @Column(name="name")
    private String name;

    @Column(name="facebook_link")
    private String facebookLink;

    @Column(name="instagram_link")
    private String instagramLink;

    @Column(name = "google_link")
    private String googleLink;

    @Column(name = "youtube_link")
    private String youtubeLink;

    @Column(name="linkedin_link")
    private String linkedinLink;

    @OneToOne(mappedBy = "user_id")
    private UserDescription userDescription;


}
