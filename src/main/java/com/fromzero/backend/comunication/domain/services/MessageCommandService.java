package com.fromzero.backend.comunication.domain.services;


import com.fromzero.backend.comunication.domain.model.aggregates.Message;
import com.fromzero.backend.comunication.domain.model.commands.CreateMessageCommand;

import java.util.Optional;

public interface MessageCommandService {
    Optional<Message> handle(CreateMessageCommand command);
}
