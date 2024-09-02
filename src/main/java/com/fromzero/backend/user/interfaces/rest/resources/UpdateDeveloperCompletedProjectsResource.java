package com.fromzero.backend.user.interfaces.rest.resources;

public record UpdateDeveloperCompletedProjectsResource(
        Long developerId,
        int addProject
) {
}