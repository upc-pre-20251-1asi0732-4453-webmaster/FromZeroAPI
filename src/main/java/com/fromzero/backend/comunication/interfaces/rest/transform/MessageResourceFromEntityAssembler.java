package com.fromzero.backend.comunication.interfaces.rest.transform;


import com.fromzero.backend.comunication.domain.model.aggregates.Message;
import com.fromzero.backend.comunication.interfaces.rest.resources.MessageResource;

public class MessageResourceFromEntityAssembler {

    public static MessageResource toResourceFromEntity(Message entity) {
        return new MessageResource(entity.getId(), entity.getSubject(), entity.getEmailBody(), entity.getRecipient(), entity.getSender(), entity.getSentTime());
    }
}
