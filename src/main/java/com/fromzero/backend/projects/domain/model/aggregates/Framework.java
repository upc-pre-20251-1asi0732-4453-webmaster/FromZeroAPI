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
public class Framework extends AuditableAbstractAggregateRoot<Framework> {

    private String name;

    @ManyToMany(mappedBy = "frameworks")
    @JsonBackReference
    private List<Project> projects;
    public Framework(String name){
        this.name = name;
    }
    public Framework() {

    }

}
