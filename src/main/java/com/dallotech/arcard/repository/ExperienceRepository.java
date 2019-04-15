package com.dallotech.arcard.repository;

import com.dallotech.arcard.model.db.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    void deleteByUser_Id(Long id);

    Optional<List<Experience>> findByUser_Id(Long id);

}
