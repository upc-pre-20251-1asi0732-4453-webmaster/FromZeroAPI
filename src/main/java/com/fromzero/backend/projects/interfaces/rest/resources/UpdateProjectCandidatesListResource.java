package com.fromzero.backend.projects.interfaces.rest.resources;


import com.fromzero.backend.user.domain.model.aggregates.Developer;

import java.util.List;

public record UpdateProjectCandidatesListResource(String name, String description, List<Developer> candidates) {
    public UpdateProjectCandidatesListResource {
        if (name == null) {
            System.out.println("name is null");
            throw new NullPointerException("name is null");
        }
        if (description == null) {
            System.out.println("description is null");
            throw new NullPointerException("description is null");
        }
    }
}
