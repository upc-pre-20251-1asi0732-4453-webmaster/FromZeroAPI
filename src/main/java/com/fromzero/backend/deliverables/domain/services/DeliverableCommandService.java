package com.fromzero.backend.deliverables.domain.services;

import com.fromzero.backend.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.backend.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.backend.deliverables.domain.model.commands.UpdateDeliverableCommand;
import com.fromzero.backend.deliverables.domain.model.commands.UpdateDeliverableStatusCommand;
import com.fromzero.backend.deliverables.domain.model.commands.UpdateDeveloperMessageCommand;

import java.util.List;
import java.util.Optional;

public interface DeliverableCommandService {
    Optional<Deliverable> handle(CreateDeliverableCommand command);
    void handle(List<CreateDeliverableCommand> commands);
    Optional<Deliverable> handle(UpdateDeveloperMessageCommand command);
    Optional<Deliverable> handle(UpdateDeliverableStatusCommand command);
    Optional<Deliverable> handle(UpdateDeliverableCommand command);
}
