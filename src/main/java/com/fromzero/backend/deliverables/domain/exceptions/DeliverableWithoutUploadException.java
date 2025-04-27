package com.fromzero.backend.deliverables.domain.exceptions;

public class DeliverableWithoutUploadException extends RuntimeException {
    public DeliverableWithoutUploadException(String message) {
        super(message);
    }
}
