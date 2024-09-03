package com.fromzero.backend.projects.domain.model.commands;


import com.fromzero.backend.projects.domain.model.aggregates.Project;

public record UpdateProjectProgressCommand(Project project, double progress) {
}
