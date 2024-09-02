package com.fromzero.backend.user.application.internal.queryservices;

import com.fromzero.backend.user.domain.model.aggregates.Enterprise;
import com.fromzero.backend.user.domain.model.queries.GetAllEnterprisesAsyncQuery;
import com.fromzero.backend.user.domain.model.queries.GetEnterpriseByIdQuery;
import com.fromzero.backend.user.domain.model.queries.GetEnterpriseByUserIdAsyncQuery;
import com.fromzero.backend.user.domain.services.EnterpriseQueryService;
import com.fromzero.backend.user.infrastructure.persistence.jpa.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseQueryServiceImpl implements EnterpriseQueryService {

    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseQueryServiceImpl(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    @Override
    public Optional<Enterprise> handle(GetEnterpriseByUserIdAsyncQuery query) {
        return enterpriseRepository.findEnterpriseByUser_Id(query.id());
    }

    @Override
    public Optional<Enterprise> handle(GetEnterpriseByIdQuery query) {
        return this.enterpriseRepository.findById(query.enterpriseId());
    }

    @Override
    public List<Enterprise> handle(GetAllEnterprisesAsyncQuery query) {
        return enterpriseRepository.findAll();
    }
}
