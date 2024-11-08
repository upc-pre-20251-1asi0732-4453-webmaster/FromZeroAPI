package com.fromzero.backend.deliverables.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateDeliverableResource(String name, String description, String date) {
}