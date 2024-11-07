package com.fromzero.backend.deliverables.interfaces.rest.resources;


public record DeliverableResource(
        Long id, String name, String description, String date, String state, String developerMessage, Long projectId) {
}
