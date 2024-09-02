package com.fromzero.backend.user.domain.services;


import com.fromzero.backend.user.domain.model.aggregates.Developer;
import com.fromzero.backend.user.domain.model.commands.UpdateDeveloperCommand;
import com.fromzero.backend.user.domain.model.commands.UpdateDeveloperCompletedProjectsCommand;

import java.util.Optional;

public interface DeveloperCommandService {
    Optional<Developer>handle(UpdateDeveloperCommand command);
    Optional<Developer> handle(UpdateDeveloperCompletedProjectsCommand command);
}
