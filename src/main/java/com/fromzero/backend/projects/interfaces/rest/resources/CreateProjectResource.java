package com.fromzero.backend.projects.interfaces.rest.resources;

import com.fromzero.backend.projects.domain.valueobjects.ProjectType;

import java.util.List;

public record CreateProjectResource(
        String name, String description, Long ownerId, List<Long> languages,
        List<Long> frameworks, ProjectType type, String budget, String methodologies){
    public CreateProjectResource{
        if(name==null){
            System.out.println("name is null");
            throw new NullPointerException("name is null");
        }
        if(description==null){
            System.out.println("description is null");
            throw new NullPointerException("description is null");
        }
        if(ownerId==null){
            System.out.println("ownerId is null");
            throw new NullPointerException("ownerId is null");
        }
        if(languages.isEmpty()){
            System.out.println("there are no languages to assign as developer");
            throw new NullPointerException("there are no languages to assign as developer");
        }
        if(frameworks.isEmpty()){
            System.out.println("there are no frameworks to assign as developer");
            throw new NullPointerException("there are no frameworks to assign as developer");
        }
    }
}
