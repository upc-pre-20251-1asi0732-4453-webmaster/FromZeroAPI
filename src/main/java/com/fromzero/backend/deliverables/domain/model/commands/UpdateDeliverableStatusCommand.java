package com.fromzero.backend.deliverables.domain.model.commands;

public record UpdateDeliverableStatusCommand(Long deliverableId, Boolean accepted) {
}
