package com.fromzero.backend.projects.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fromzero.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class ProgrammingLanguage extends AuditableAbstractAggregateRoot<ProgrammingLanguage> {

    private String name;

    @ManyToMany(mappedBy = "languages")
    @JsonBackReference
    private List<Project> projects;

    public ProgrammingLanguage(String name){
        this.name=name;
    }
    public ProgrammingLanguage(){

    }
}
