package com.fromzero.backend.comunication.interfaces.rest.resources;

public record CreateMessageResource(String subject, String emailBody, Long recipientId, Long senderId) {
    public CreateMessageResource {
        if (subject == null) {
            System.out.println("subject is null");
            throw new NullPointerException("subject is null");
        }
        if (emailBody == null) {
            System.out.println("emailBody is null");
            throw new NullPointerException("emailBody is null");
        }
        if (recipientId == null) {
            System.out.println("recipientId is null");
            throw new NullPointerException("recipientId is null");
        }
        if (senderId == null) {
            System.out.println("senderId is null");
            throw new NullPointerException("senderId is null");
        }
    }
}
