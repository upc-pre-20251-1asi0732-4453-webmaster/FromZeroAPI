package com.fromzero.backend.projects.domain.model.queries;


import com.fromzero.backend.user.domain.model.aggregates.Developer;

public record GetAllProjectsByDeveloperIdQuery(Developer developer) {
}
