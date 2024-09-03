package com.fromzero.backend.comunication.application.internal.queryServices;

import com.fromzero.backend.comunication.domain.model.aggregates.Message;
import com.fromzero.backend.comunication.domain.model.queries.GetAllMessageByRecipientByIdQuery;
import com.fromzero.backend.comunication.domain.model.queries.GetMessageByIdQuery;
import com.fromzero.backend.comunication.domain.services.MessageQueryService;
import com.fromzero.backend.comunication.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageRepository messageRepository;

    public MessageQueryServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> handle(GetMessageByIdQuery query) {
        return this.messageRepository.findById(query.id());
    }

    @Override
    public List<Message> handle(GetAllMessageByRecipientByIdQuery query) {
        return this.messageRepository.findAllByRecipient(query.recipient());
    }
}
