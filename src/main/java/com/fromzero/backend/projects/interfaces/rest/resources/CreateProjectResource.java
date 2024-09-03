package com.fromzero.backend.projects.interfaces.rest.resources;

import java.util.List;

public record CreateProjectResource(
        String name, String description,Long ownerId,List<Long> languages,
        List<Long> frameworks,String type,String budget, String methodologies){

}
