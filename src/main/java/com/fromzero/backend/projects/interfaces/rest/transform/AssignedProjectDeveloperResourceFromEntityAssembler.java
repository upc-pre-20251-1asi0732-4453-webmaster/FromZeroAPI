package com.fromzero.backend.projects.interfaces.rest.transform;


import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.projects.interfaces.rest.resources.AssignProjectDeveloperResource;

public class AssignedProjectDeveloperResourceFromEntityAssembler {
    public static AssignProjectDeveloperResource toResourceFromEntity(Project entity){
        return new AssignProjectDeveloperResource(entity.getName(),entity.getDescription(),
                entity.getState().name(),entity.getDeveloper(),entity.getCandidates());
    }
}
