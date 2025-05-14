package com.fromzero.backend.projects.interfaces.rest.resources;

//import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;

import com.fromzero.backend.projects.domain.model.aggregates.Framework;
import com.fromzero.backend.projects.domain.model.aggregates.ProgrammingLanguage;
import com.fromzero.backend.projects.domain.valueobjects.ProjectType;
import com.fromzero.backend.user.domain.model.aggregates.Developer;
import lombok.Getter;

import java.util.List;

public record ProjectResource(
        Long id, String name, String description, String state, Double progress,
        Long ownerId, Long developerId, List<Developer> candidatesList,
        List<ProgrammingLanguage> languages, List<Framework> frameworks,String type,
        String budget,String methodologies) {
    public ProjectResource{
        if(id==null || name==null || description==null){
            throw new NullPointerException("id is null");
        }
    }

    public ProjectResource(Long id,String name, String description, String state,
                           Double progress, Long ownerId,List<Developer> candidatesList,
                           List<ProgrammingLanguage> languages, List<Framework> frameworks,
                           String type,String budget,String methodologies){
        this(id,name,description,state,progress,ownerId,null,candidatesList
                ,languages,frameworks, type,budget,methodologies);
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String state() {
        return state;
    }

    @Override
    public Double progress() {
        return progress;
    }

    @Override
    public Long ownerId() {
        return ownerId;
    }

    @Override
    public Long developerId() {
        return developerId;
    }

    @Override
    public List<Developer> candidatesList() {
        return candidatesList;
    }

    @Override
    public List<ProgrammingLanguage> languages() {
        return languages;
    }

    @Override
    public List<Framework> frameworks() {
        return frameworks;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public String budget() {
        return budget;
    }

    @Override
    public String methodologies() {
        return methodologies;
    }
}
