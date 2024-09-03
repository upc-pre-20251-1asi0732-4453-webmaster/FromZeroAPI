package com.fromzero.backend.comunication.domain.model.commands;


import com.fromzero.backend.iam.domain.model.aggregates.User;

public record CreateMessageCommand(String subject, String emailBody, User recipient, User sender) {
}
