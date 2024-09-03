package com.fromzero.backend.comunication.domain.model.queries;


import com.fromzero.backend.iam.domain.model.aggregates.User;

public record GetAllMessageByRecipientByIdQuery(User recipient) {

}
