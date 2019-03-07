package com.dallotech.arcard.service;

import com.dallotech.arcard.exception.UserAuthenticationFailedException;
import com.dallotech.arcard.exception.UserEmailNotFound;
import com.dallotech.arcard.model.db.User;
import com.dallotech.arcard.model.internal.LoggedUser;
import com.dallotech.arcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("sessionService")
public class SessionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public LoggedUser getLoggedInUserDetails() throws UserAuthenticationFailedException {
        // get logged in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof UserDetails))
            throw new UserAuthenticationFailedException();
        String loggedInUserEmail = ((UserDetails) principal).getUsername();
        if (userRepository.existsByEmail(loggedInUserEmail)) {
            User user = userRepository.findByEmail(loggedInUserEmail).get();
            return new LoggedUser(user.getUuid(),user);
        } else {
            throw new UserEmailNotFound(loggedInUserEmail);
        }
    }

    public LoggedUser verifyAndGetLoggedInUser() {
        try {
            return getLoggedInUserDetails();
        } catch (UserAuthenticationFailedException e) {
            throw new Error(e.getMessage());
        }
    }

}
