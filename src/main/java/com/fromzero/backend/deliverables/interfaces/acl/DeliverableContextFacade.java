package com.fromzero.backend.deliverables.interfaces.acl;

import com.fromzero.backend.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.backend.deliverables.domain.services.DeliverableCommandService;
import com.fromzero.backend.projects.domain.model.aggregates.Project;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class DeliverableContextFacade {

    private final DeliverableCommandService deliverableCommandService;

    public DeliverableContextFacade(DeliverableCommandService deliverableCommandService) {
        this.deliverableCommandService = deliverableCommandService;
    }
}