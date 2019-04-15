package com.dallotech.arcard.model.internal;

import com.dallotech.arcard.model.db.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUser {

    private Long userId;
    private User user;


}
