package com.dallotech.arcard.model.db;

import com.dallotech.arcard.model.dto.SignUpResponseDto;
import com.dallotech.arcard.model.dto.SignupRequestDto;
import com.dallotech.arcard.model.dto.UserDto;
import com.dallotech.arcard.utils.StringListConverter;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name="email",unique = true)
    String email;

    @Column(name="password")
    String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();

    @Column(name="first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name="about_me")
    private String aboutMe;

    @Column(name = "skills")
    @Convert(converter = StringListConverter.class)
    private List<String> skills=new ArrayList<>();

    @Column(name="projects")
    @Convert(converter = StringListConverter.class)
    private List<String> projects=new ArrayList<>();

    @Column(name = "activities")
    @Convert(converter = StringListConverter.class)
    private List<String> activities=new ArrayList<>();

    @Column(name="facebook_link")
    private String facebookLink;

    @Column(name="instagram_link")
    private String instagramLink;

    @Column(name = "twitter_link")
    private String twitterLink;

    @Column(name="linkedin_link")
    private String linkedinLink;

    @Column(name="portfolio_link")
    private String protfolioLink;

    @Column(name = "image_path")
    private String imagePath;


    public static User getUserFromUserDto(SignupRequestDto signupRequestDto){
        User user=new User();
        user.setFirstName(signupRequestDto.getFirstName());
        user.setMiddleName(signupRequestDto.getMiddleName());
        user.setLastName(signupRequestDto.getLastName());
        user.setEmail(signupRequestDto.getEmail());
        return user;
    }


}
