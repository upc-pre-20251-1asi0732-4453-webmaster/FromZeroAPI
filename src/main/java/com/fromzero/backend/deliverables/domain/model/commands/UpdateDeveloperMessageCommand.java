package com.fromzero.backend.deliverables.domain.model.commands;

public record UpdateDeveloperMessageCommand(Long deliverableId, String message, Long project) {
}
