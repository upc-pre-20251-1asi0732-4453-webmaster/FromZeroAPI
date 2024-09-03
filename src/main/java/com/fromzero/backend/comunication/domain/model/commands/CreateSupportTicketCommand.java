package com.fromzero.backend.comunication.domain.model.commands;


import com.fromzero.backend.iam.domain.model.aggregates.User;

public record CreateSupportTicketCommand(String title, String type, String description, User sender){
}
