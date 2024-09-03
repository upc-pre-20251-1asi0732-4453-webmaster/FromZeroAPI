package com.fromzero.backend.comunication.interfaces.rest.resources;

public record CreateMessageResource(String subject, String emailBody, Long recipientId, Long senderId) {
}
