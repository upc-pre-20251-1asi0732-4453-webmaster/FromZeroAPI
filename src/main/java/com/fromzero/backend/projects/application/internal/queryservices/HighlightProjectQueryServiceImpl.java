package com.fromzero.backend.projects.application.internal.queryservices;


import com.fromzero.backend.projects.domain.model.entities.HighlightProject;
import com.fromzero.backend.projects.domain.model.queries.GetAllHighlightProjectsQuery;
import com.fromzero.backend.projects.domain.services.HighlightProjectsQueryService;
import com.fromzero.backend.projects.infrastructure.persistence.jpa.repositories.HighlightProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HighlightProjectQueryServiceImpl implements HighlightProjectsQueryService {
    private final HighlightProjectRepository highlightProjectRepository;

    public HighlightProjectQueryServiceImpl(HighlightProjectRepository highlightProjectRepository) {
        this.highlightProjectRepository = highlightProjectRepository;
    }
    @Override
    public List<HighlightProject> handle(GetAllHighlightProjectsQuery query) {
        return highlightProjectRepository.findAll();
    }
}
