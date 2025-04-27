package com.fromzero.backend.deliverables.domain.exceptions;

public class DeliverableAlreadyApprovedException extends RuntimeException {
    public DeliverableAlreadyApprovedException(String message) {
        super(message);
    }
}
