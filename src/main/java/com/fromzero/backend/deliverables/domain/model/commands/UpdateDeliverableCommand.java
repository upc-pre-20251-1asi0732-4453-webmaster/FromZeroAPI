package com.fromzero.backend.deliverables.domain.model.commands;

import java.time.LocalDate;

public record UpdateDeliverableCommand(Long deliverableId, String name, String description, String date) {
}