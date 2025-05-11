package com.fromzero.backend.projects.domain.model.queries;

import com.fromzero.backend.projects.domain.valueobjects.ProjectState;

public record GetAllProjectsByStateQuery(ProjectState state) {
    public GetAllProjectsByStateQuery {
        if (state == null ) {
            throw new IllegalArgumentException("state cannot be null or empty");
        }
    }
}
