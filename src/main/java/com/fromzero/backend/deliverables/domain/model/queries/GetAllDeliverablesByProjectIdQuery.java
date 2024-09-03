package com.fromzero.backend.deliverables.domain.model.queries;


import com.fromzero.backend.projects.domain.model.aggregates.Project;

public record GetAllDeliverablesByProjectIdQuery(Project project) {
}
