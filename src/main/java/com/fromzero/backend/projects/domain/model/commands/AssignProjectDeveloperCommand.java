package com.fromzero.backend.projects.domain.model.commands;

import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.user.domain.model.aggregates.Developer;

public record AssignProjectDeveloperCommand(Project project, Developer developer) {
}
