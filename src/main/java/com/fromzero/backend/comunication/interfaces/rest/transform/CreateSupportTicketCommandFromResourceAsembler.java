package com.fromzero.backend.comunication.interfaces.rest.transform;


import com.fromzero.backend.comunication.domain.model.commands.CreateSupportTicketCommand;
import com.fromzero.backend.comunication.interfaces.rest.resources.CreateSupportTicketResource;
import com.fromzero.backend.iam.domain.model.aggregates.User;

public class CreateSupportTicketCommandFromResourceAsembler {

    public static CreateSupportTicketCommand toCommandFromResource(CreateSupportTicketResource resource, User sender){
        return new CreateSupportTicketCommand(resource.title(), resource.type(), resource.description(), sender);
    }
}
