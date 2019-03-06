package com.dallotech.arcard.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDto {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;



}
