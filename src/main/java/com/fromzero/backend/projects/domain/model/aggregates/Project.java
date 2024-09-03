package com.fromzero.backend.projects.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fromzero.backend.projects.domain.model.commands.CreateProjectCommand;
import com.fromzero.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.fromzero.backend.user.domain.model.aggregates.Developer;
import com.fromzero.backend.user.domain.model.aggregates.Enterprise;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Project extends AuditableAbstractAggregateRoot<Project> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Setter
    private String state;

    @Setter
    private Double progress;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Setter
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_candidates",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    @JsonManagedReference
    private List<Developer> candidates;

    //many to many relationship
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_programming_languages",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "programming_language_id")
    )
    @JsonManagedReference
    private List<ProgrammingLanguage> languages;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_frameworks",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "framework_id")
    )
    @JsonManagedReference
    private List<Framework> frameworks;

    private String type;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String budget;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String methodologies;

    public Project(CreateProjectCommand command){
        this.name=command.name();
        this.description=command.description();
        this.state="En busqueda";
        this.progress=0.0;
        this.enterprise=command.enterprise();
        this.developer=null;
        this.languages=new ArrayList<>();
        this.frameworks=new ArrayList<>();
        this.type=command.type();
        this.budget=command.budget();
        this.methodologies=command.methodologies();

    }

    public Project(){

    }
}
