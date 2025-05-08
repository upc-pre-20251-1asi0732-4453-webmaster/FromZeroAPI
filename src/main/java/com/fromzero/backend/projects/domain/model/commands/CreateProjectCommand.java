package com.fromzero.backend.projects.domain.model.commands;


import com.fromzero.backend.projects.domain.model.aggregates.Framework;
import com.fromzero.backend.projects.domain.model.aggregates.ProgrammingLanguage;
import com.fromzero.backend.projects.domain.valueobjects.ProjectType;
import com.fromzero.backend.user.domain.model.aggregates.Enterprise;

import java.util.List;

public record CreateProjectCommand(
        String name, String description, Enterprise enterprise,
        List<ProgrammingLanguage> languages, List<Framework> frameworks, ProjectType type,
        String budget, String methodologies) {

}