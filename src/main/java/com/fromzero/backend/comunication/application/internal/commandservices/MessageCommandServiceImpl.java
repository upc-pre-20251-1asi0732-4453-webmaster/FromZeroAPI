package com.fromzero.backend.comunication.application.internal.commandservices;

import com.fromzero.backend.comunication.domain.model.aggregates.Message;
import com.fromzero.backend.comunication.domain.model.commands.CreateMessageCommand;
import com.fromzero.backend.comunication.domain.services.MessageCommandService;
import com.fromzero.backend.comunication.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {

    private final MessageRepository messageRepository;

    public MessageCommandServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> handle(CreateMessageCommand command) {
        var message = new Message(command);
        this.messageRepository.save(message);
        return Optional.of(message);
    }
}
