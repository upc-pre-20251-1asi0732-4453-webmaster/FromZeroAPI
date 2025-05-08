package com.fromzero.backend.projects.interfaces.rest.resources;

//import com.acme.fromzeroapi.developer_branch_projects.domain.model.aggregates.Developer;

import com.fromzero.backend.projects.domain.model.aggregates.Framework;
import com.fromzero.backend.projects.domain.model.aggregates.ProgrammingLanguage;
import com.fromzero.backend.projects.domain.valueobjects.ProjectType;
import com.fromzero.backend.user.domain.model.aggregates.Developer;

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


}
