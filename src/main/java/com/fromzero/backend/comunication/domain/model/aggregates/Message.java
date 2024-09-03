package com.fromzero.backend.comunication.domain.model.aggregates;


import com.fromzero.backend.comunication.domain.model.commands.CreateMessageCommand;
import com.fromzero.backend.iam.domain.model.aggregates.User;
import com.fromzero.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Message extends AuditableAbstractAggregateRoot<Message> {

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String emailBody;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false)
    private LocalDate sentTime;

    public Message(CreateMessageCommand createMessageCommand) {
        this.subject = createMessageCommand.subject();
        this.emailBody = createMessageCommand.emailBody();
        this.recipient = createMessageCommand.recipient();
        this.sender = createMessageCommand.sender();
        this.sentTime = LocalDate.now();
    }

    public Message() {}


}
