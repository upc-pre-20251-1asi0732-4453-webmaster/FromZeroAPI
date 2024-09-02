package com.fromzero.backend.user.domain.model.commands;


import com.fromzero.backend.user.domain.model.aggregates.Developer;

public record UpdateDeveloperCompletedProjectsCommand(Developer developer, int addProject) {
}
