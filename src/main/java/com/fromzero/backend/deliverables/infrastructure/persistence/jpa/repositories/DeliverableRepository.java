package com.fromzero.backend.deliverables.infrastructure.persistence.jpa.repositories;

import com.fromzero.backend.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.backend.projects.domain.model.aggregates.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {
    List<Deliverable> findAllByProject(Project project);
}
