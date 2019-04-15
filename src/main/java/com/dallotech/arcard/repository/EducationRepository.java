package com.dallotech.arcard.repository;

import com.dallotech.arcard.model.db.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EducationRepository extends JpaRepository<Education, Long> {

    void deleteByUser_Id(Long id);

    Optional<List<Education>> findByUser_Id(Long id);
}
