package com.fromzero.backend.deliverables.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fromzero.backend.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.backend.deliverables.domain.valueobjects.DeliverableStatus;
import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Entity
@Setter
@Getter
public class Deliverable extends AuditableAbstractAggregateRoot<Deliverable> {
    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime deadline; //format: "2025-08-15T14:30:45"


    @Column(nullable = false)
    private DeliverableStatus state;

    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Setter
    @Lob
    @Column(columnDefinition = "TEXT")
    private String developerDescription;


    @Setter
    @Getter
    @Column(nullable = false)
    private int orderNumber;


    public Deliverable(CreateDeliverableCommand command, Project project) {
        this.name=command.name();
        this.description=command.description();
        this.deadline= LocalDateTime.parse(command.date());
        this.state=DeliverableStatus.PENDING;
        this.developerDescription =null;
        this.fileUrl="https://i.scdn.co/image/ab67616d0000b273fb31e9fdfd96144812da8e5f";
        this.project = project;
        this.orderNumber=command.orderNumber();
    }

    public Deliverable() {

    }

}
