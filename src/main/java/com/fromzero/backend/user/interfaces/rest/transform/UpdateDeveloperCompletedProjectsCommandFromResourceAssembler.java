package com.fromzero.backend.user.interfaces.rest.transform;

import com.fromzero.backend.user.domain.model.commands.UpdateDeveloperCompletedProjectsCommand;
import com.fromzero.backend.user.infrastructure.persistence.jpa.repositories.DeveloperRepository;
import com.fromzero.backend.user.interfaces.rest.resources.UpdateDeveloperCompletedProjectsResource;
import com.fromzero.backend.user.domain.model.aggregates.Developer;

public class UpdateDeveloperCompletedProjectsCommandFromResourceAssembler {
    private final DeveloperRepository developerRepository;

    public UpdateDeveloperCompletedProjectsCommandFromResourceAssembler(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public UpdateDeveloperCompletedProjectsCommand toCommandFromResource(UpdateDeveloperCompletedProjectsResource resource) {
        Developer developer = developerRepository.findById(resource.developerId())
                .orElseThrow(() -> new RuntimeException("Developer not found")); // Reemplaza esto con tu propia excepción personalizada

        return new UpdateDeveloperCompletedProjectsCommand(
                developer,
                resource.addProject()
        );
    }
}