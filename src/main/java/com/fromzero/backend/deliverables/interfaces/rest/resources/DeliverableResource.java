package com.fromzero.backend.deliverables.interfaces.rest.resources;



import com.fromzero.backend.projects.domain.model.aggregates.Project;

import java.time.LocalDate;

public record DeliverableResource(
        Long id, String name, String description, LocalDate date, String state, String developerMessage, Project project) {
}
