package com.fromzero.backend.deliverables.interfaces.rest.transform;


import com.fromzero.backend.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.backend.deliverables.interfaces.rest.resources.DeliverableResource;

public class DeliverableResourceFromEntityAssembler {
    public static DeliverableResource toResourceFromEntity(Deliverable entity){
        return new DeliverableResource(
                entity.getId(),entity.getName(),entity.getDescription(),
                entity.getDate(),entity.getState(),entity.getDeveloperMessage(),entity.getProject());
    }
}
