package com.fromzero.backend.projects.domain.services;


import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.projects.domain.model.commands.*;

import java.util.Optional;

public interface ProjectCommandService {
    Optional<Project> handle(CreateProjectCommand command);
    Optional<Project> handle(UpdateProjectCandidatesListCommand command);
    Optional<Project> handle(AssignProjectDeveloperCommand command);
    Optional<Project> handle(UpdateProjectProgressCommand command);
    void handle(DeleteProjectCommand command);
}
