package com.dallotech.arcard.model.dto;

import com.dallotech.arcard.payload.AuthResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpResponseDto {

    @JsonProperty("auth_response")
    private AuthResponse authResponse;
    @JsonProperty("user_info")
    private UserDto userDto;

}
