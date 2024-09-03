package com.fromzero.backend.deliverables.interfaces.rest.transform;


import com.fromzero.backend.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.backend.deliverables.interfaces.rest.resources.CreateDeliverableResource;
import com.fromzero.backend.projects.domain.model.aggregates.Project;

public class CreateDeliverableCommandFromResourceAssembler {
    public static CreateDeliverableCommand toCommandFromResource(CreateDeliverableResource resource, Project project){
        return new CreateDeliverableCommand(resource.name(),resource.description(),resource.date(),project);
    }
}
