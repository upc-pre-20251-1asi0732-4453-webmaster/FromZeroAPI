package com.fromzero.backend.user.infrastructure.persistence.jpa.repositories;

import com.fromzero.backend.user.domain.model.aggregates.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    Optional<Enterprise> findEnterpriseByUser_Id(Long id);
}
