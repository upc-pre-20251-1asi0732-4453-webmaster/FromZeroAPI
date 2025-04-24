package com.fromzero.backend.deliverables.domain.model.aggregates;

import com.fromzero.backend.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.backend.deliverables.domain.valueobjects.DeliverableStatus;
import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private DeliverableStatus state;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Setter
    @Lob
    @Column(columnDefinition = "TEXT")
    private String developerMessage;


    @Setter
    @Getter
    @Column(nullable = false)
    private int orderNumber;


    public Deliverable(CreateDeliverableCommand command, Project project) {
        this.name=command.name();
        this.description=command.description();
        this.date= String.valueOf(command.date());
        this.state=DeliverableStatus.PENDING;
        this.developerMessage=null;
        this.project = project;
        this.orderNumber=0;
    }

    public Deliverable() {

    }
}
