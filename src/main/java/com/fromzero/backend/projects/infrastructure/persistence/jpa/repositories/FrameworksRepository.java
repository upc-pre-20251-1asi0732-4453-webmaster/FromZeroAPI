package com.fromzero.backend.projects.infrastructure.persistence.jpa.repositories;


import com.fromzero.backend.projects.domain.model.aggregates.Framework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FrameworksRepository extends JpaRepository<Framework, Integer> {
    Optional<Framework> findFrameworkById(Long id);
}
