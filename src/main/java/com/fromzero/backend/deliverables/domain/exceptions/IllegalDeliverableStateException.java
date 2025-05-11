package com.fromzero.backend.deliverables.domain.exceptions;

public class IllegalDeliverableStateException extends RuntimeException {
    public IllegalDeliverableStateException(String message) {
        super(message);
    }
}
