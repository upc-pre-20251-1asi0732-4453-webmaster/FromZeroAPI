package com.fromzero.backend.user.domain.services;

import com.fromzero.backend.user.domain.model.aggregates.Enterprise;
import com.fromzero.backend.user.domain.model.queries.GetAllEnterprisesAsyncQuery;
import com.fromzero.backend.user.domain.model.queries.GetEnterpriseByIdQuery;
import com.fromzero.backend.user.domain.model.queries.GetEnterpriseByUserIdAsyncQuery;

import java.util.List;
import java.util.Optional;

public interface EnterpriseQueryService {
    Optional<Enterprise> handle(GetEnterpriseByUserIdAsyncQuery query);
    Optional<Enterprise> handle(GetEnterpriseByIdQuery query);
    List<Enterprise> handle(GetAllEnterprisesAsyncQuery query);
}
