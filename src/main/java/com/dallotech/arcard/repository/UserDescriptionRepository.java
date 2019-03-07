package com.dallotech.arcard.repository;

import com.dallotech.arcard.model.db.UserDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDescriptionRepository extends JpaRepository<UserDescription,Long> {

}
