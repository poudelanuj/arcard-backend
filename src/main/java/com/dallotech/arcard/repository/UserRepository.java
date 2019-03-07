package com.dallotech.arcard.repository;


import com.dallotech.arcard.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

     Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByUuid(UUID uuidNo);

}
