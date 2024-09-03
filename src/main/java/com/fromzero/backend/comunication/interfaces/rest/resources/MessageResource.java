package com.fromzero.backend.comunication.interfaces.rest.resources;


import com.fromzero.backend.iam.domain.model.aggregates.User;

import java.time.LocalDate;

public record MessageResource(Long id, String subject, String emailBody, User recipient, User sender, LocalDate sentTime) {
}
