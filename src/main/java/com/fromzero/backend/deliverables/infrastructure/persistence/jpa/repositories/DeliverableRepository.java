package com.fromzero.backend.deliverables.infrastructure.persistence.jpa.repositories;

import com.fromzero.backend.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.backend.projects.domain.model.aggregates.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Long> {
    List<Deliverable> findAllByProject(Project project);

    @Query("SELECT MAX(d.orderNumber) FROM Deliverable d WHERE d.project.id = :projectId")
    Integer findMaxOrderNumberByProject(@Param("projectId") Long projectId);

    Optional<Deliverable> findByProjectIdAndOrderNumber(Long projectId, int orderNumber);

    void deleteAllByProjectId(Long projectId);

}
