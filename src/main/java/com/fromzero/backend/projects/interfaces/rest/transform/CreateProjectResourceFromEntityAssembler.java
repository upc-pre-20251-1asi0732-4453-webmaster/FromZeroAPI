package com.fromzero.backend.projects.interfaces.rest.transform;



import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.projects.interfaces.rest.resources.CreateProjectResource;

import com.fromzero.backend.projects.domain.model.aggregates.Framework;
import com.fromzero.backend.projects.domain.model.aggregates.ProgrammingLanguage;

import java.util.List;
import java.util.stream.Collectors;

public class CreateProjectResourceFromEntityAssembler {
    public static CreateProjectResource toResourceFromEntity(Project entity) {
        List<Long> languageIds = entity.getLanguages().stream()
                .map(ProgrammingLanguage::getId)
                .collect(Collectors.toList());

        // Transformar la lista de frameworks a una lista de IDs
        List<Long> frameworkIds = entity.getFrameworks().stream()
                .map(Framework::getId)
                .collect(Collectors.toList());
        return new CreateProjectResource(entity.getName(), entity.getDescription(), entity.getEnterprise().getId(),
                languageIds,frameworkIds,entity.getType(),entity.getBudget(),entity.getMethodologies());
    }
}
