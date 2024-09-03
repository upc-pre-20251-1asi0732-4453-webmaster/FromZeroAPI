package com.fromzero.backend.projects.interfaces.rest.transform;


import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.projects.interfaces.rest.resources.UpdateProjectCandidatesListResource;

public class UpdatedProjectResourceFromEntityAssembler {
    public static UpdateProjectCandidatesListResource toResourceFromEntity(Project entity){
        return new UpdateProjectCandidatesListResource(entity.getName(),entity.getDescription(),entity.getCandidates());
    }

}
