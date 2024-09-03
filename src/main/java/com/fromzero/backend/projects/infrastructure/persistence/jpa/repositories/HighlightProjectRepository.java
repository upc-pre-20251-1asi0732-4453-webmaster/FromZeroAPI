package com.fromzero.backend.projects.infrastructure.persistence.jpa.repositories;


import com.fromzero.backend.projects.domain.model.entities.HighlightProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighlightProjectRepository extends JpaRepository<HighlightProject, Long> {
}
