package com.fromzero.backend.projects.interfaces.rest.resources;


import com.fromzero.backend.user.domain.model.aggregates.Developer;

import java.util.List;

public record UpdateProjectCandidatesListResource(String name, String description, List<Developer> candidates) {
}
