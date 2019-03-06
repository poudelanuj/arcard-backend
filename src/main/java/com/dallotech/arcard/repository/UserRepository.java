package com.dallotech.arcard.repository;


import com.dallotech.arcard.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);


}
