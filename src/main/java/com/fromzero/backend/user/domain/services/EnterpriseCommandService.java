package com.fromzero.backend.user.domain.services;

import com.fromzero.backend.user.domain.model.aggregates.Enterprise;
import com.fromzero.backend.user.domain.model.commands.UpdateEnterpriseCommand;

import java.util.Optional;

public interface EnterpriseCommandService {
    Optional<Enterprise> handle(UpdateEnterpriseCommand command);

}
