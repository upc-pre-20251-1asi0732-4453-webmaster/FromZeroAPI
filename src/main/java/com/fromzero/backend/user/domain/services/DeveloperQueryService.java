package com.fromzero.backend.user.domain.services;

import com.fromzero.backend.user.domain.model.aggregates.Developer;
import com.fromzero.backend.user.domain.model.queries.GetAllDevelopersAsyncQuery;
import com.fromzero.backend.user.domain.model.queries.GetDeveloperByIdQuery;
import com.fromzero.backend.user.domain.model.queries.GetDeveloperByUserIdAsyncQuery;

import java.util.List;
import java.util.Optional;

public interface DeveloperQueryService {
    List<Developer> handle(GetAllDevelopersAsyncQuery query);
    Optional<Developer> handle(GetDeveloperByUserIdAsyncQuery query);
    Optional<Developer> handle(GetDeveloperByIdQuery query);

}
