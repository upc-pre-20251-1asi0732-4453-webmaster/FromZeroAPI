package com.fromzero.backend.deliverables.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateDeliverableResource(
        String name, String description, String date, Long projectId) {
}
