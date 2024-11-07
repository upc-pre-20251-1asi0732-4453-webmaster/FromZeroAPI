package com.fromzero.backend.deliverables.domain.model.aggregates;

import com.fromzero.backend.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    private String date;

    @Setter
    @Column(nullable = false)
    private String state;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Setter
    @Lob
    @Column(columnDefinition = "TEXT")
    private String developerMessage;

    public Deliverable(CreateDeliverableCommand command) {
        this.name=command.name();
        this.description=command.description();
        this.date= String.valueOf(command.date());
        this.state="Pending";
        this.developerMessage=null;
        this.project=command.project();
    }

    public Deliverable() {

    }
}
