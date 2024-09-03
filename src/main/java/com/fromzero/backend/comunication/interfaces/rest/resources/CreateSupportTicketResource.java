package com.fromzero.backend.comunication.interfaces.rest.resources;

public record CreateSupportTicketResource(String title, String type, String description, Long senderId) {
}
