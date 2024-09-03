package com.fromzero.backend.projects.domain.services;


import com.fromzero.backend.projects.domain.model.entities.HighlightProject;
import com.fromzero.backend.projects.domain.model.queries.GetAllHighlightProjectsQuery;

import java.util.List;

public interface HighlightProjectsQueryService {
    List<HighlightProject> handle(GetAllHighlightProjectsQuery query);
}
