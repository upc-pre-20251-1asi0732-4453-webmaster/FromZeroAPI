package com.fromzero.backend.deliverables.domain.exceptions;

public class IllegalDeliverableDeadlineDateException extends RuntimeException {
    public IllegalDeliverableDeadlineDateException(String message) {
        super(message);
    }
}
