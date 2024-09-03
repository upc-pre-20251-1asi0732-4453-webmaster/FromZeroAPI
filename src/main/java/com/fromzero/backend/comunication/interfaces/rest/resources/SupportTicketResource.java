package com.fromzero.backend.comunication.interfaces.rest.resources;


import com.fromzero.backend.iam.domain.model.aggregates.User;

import java.time.LocalDate;

public record SupportTicketResource(Long id, String title, String type, String description, User sender, LocalDate creationDate){
}
