package com.fromzero.backend.deliverables.interfaces.rest.transform;


import com.fromzero.backend.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.backend.deliverables.interfaces.rest.resources.CreateDeliverableResource;

public class CreateDeliverableCommandFromResourceAssembler {
    public static CreateDeliverableCommand toCommandFromResource(CreateDeliverableResource resource){
        return new CreateDeliverableCommand(resource.name(),resource.description(),resource.date(),resource.projectId(),1);
    }
}
