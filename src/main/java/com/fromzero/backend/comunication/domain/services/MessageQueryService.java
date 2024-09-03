package com.fromzero.backend.comunication.domain.services;



import com.fromzero.backend.comunication.domain.model.aggregates.Message;
import com.fromzero.backend.comunication.domain.model.queries.GetAllMessageByRecipientByIdQuery;
import com.fromzero.backend.comunication.domain.model.queries.GetMessageByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MessageQueryService {

    Optional<Message> handle(GetMessageByIdQuery query);
    List<Message> handle(GetAllMessageByRecipientByIdQuery query);

}
