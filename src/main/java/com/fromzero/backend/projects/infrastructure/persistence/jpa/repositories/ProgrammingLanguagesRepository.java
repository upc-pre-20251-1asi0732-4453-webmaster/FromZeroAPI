package com.fromzero.backend.projects.infrastructure.persistence.jpa.repositories;


import com.fromzero.backend.projects.domain.model.aggregates.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgrammingLanguagesRepository extends JpaRepository<ProgrammingLanguage, Long> {
    Optional<ProgrammingLanguage> findProgrammingLanguageById(Long id);
}
