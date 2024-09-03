package com.fromzero.backend.projects.domain.services;


import com.fromzero.backend.projects.domain.model.aggregates.Framework;
import com.fromzero.backend.projects.domain.model.queries.GetFrameworkByIdQuery;

import java.util.Optional;

public interface FrameworksQueryService {
    Optional<Framework> handle(GetFrameworkByIdQuery query);
}
