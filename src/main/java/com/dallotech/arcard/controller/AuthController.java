package com.dallotech.arcard.controller;

import com.dallotech.arcard.model.db.Address;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.db.UserDescription;
import com.dallotech.arcard.model.dto.LoginDto;
import com.dallotech.arcard.model.dto.SignUpResponseDto;
import com.dallotech.arcard.model.dto.SignupRequestDto;
import com.dallotech.arcard.model.dto.UserDto;
import com.dallotech.arcard.payload.ApiResponse;
import com.dallotech.arcard.payload.AuthResponse;
import com.dallotech.arcard.repository.UserRepository;
import com.dallotech.arcard.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto){

        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token, "Bearer"));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDto signupRequestDto){

        if (userRepository.existsByEmail(signupRequestDto.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user=new User();
        user.setFirstName(signupRequestDto.getFirstName());
        user.setLastName(signupRequestDto.getLastName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        UserDescription userDescription=new UserDescription();
        Address address=new Address();
        user.setUserDescription(userDescription);
        user.setAddress(address);
        User result = userRepository.save(user);
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signupRequestDto.getEmail(),
                        signupRequestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        SignUpResponseDto signUpResponseDto=new SignUpResponseDto();
        signUpResponseDto.setAuthResponse(new AuthResponse(token,"Bearer"));
        signUpResponseDto.setUserDto(UserDto.getUserDtoFromUser(result));

        return ResponseEntity.ok(signUpResponseDto);




    }


    @PostMapping("/sign_out")
    public ResponseEntity signOut(HttpServletResponse response) {
        Cookie cookie = new Cookie("JwtSessionCookie", null);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok("You are logged out.");
    }

}
