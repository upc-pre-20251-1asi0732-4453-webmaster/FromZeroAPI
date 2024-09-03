package com.fromzero.backend.deliverables.domain.model.commands;

import com.fromzero.backend.projects.domain.model.aggregates.Project;

import java.time.LocalDate;

public record CreateDeliverableCommand(
        String name, String description, LocalDate date, Project project) {
}
