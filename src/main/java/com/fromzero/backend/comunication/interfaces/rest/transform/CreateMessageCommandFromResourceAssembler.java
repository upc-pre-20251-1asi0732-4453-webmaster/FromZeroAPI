package com.fromzero.backend.comunication.interfaces.rest.transform;


import com.fromzero.backend.comunication.domain.model.commands.CreateMessageCommand;
import com.fromzero.backend.comunication.interfaces.rest.resources.CreateMessageResource;
import com.fromzero.backend.iam.domain.model.aggregates.User;

public class CreateMessageCommandFromResourceAssembler {

    public static CreateMessageCommand toCommandFromResource(CreateMessageResource resource, User recipient, User sender){
        return new CreateMessageCommand(resource.subject(), resource.emailBody(), recipient, sender);
    }

}
