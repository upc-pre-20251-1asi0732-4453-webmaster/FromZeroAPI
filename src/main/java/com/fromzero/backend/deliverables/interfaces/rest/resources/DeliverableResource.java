package com.fromzero.backend.deliverables.interfaces.rest.resources;


import com.fromzero.backend.deliverables.domain.valueobjects.DeliverableStatus;

public record DeliverableResource(
        Long id, String name, String description, String date, DeliverableStatus state, String developerMessage, Long projectId) {
}
