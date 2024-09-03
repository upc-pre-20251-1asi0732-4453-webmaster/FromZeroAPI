package com.fromzero.backend.projects.interfaces.rest.resources;


import com.fromzero.backend.user.domain.model.aggregates.Developer;

import java.util.List;

public record AssignProjectDeveloperResource(
        String name, String description, String state,
        Developer developer, List<Developer> candidates) {
}
