package com.fromzero.backend.projects.interfaces.rest.transform;


import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.projects.domain.valueobjects.ProjectState;
import com.fromzero.backend.projects.interfaces.rest.resources.ProjectResource;

public class ProjectResourceFromEntityAssembler {
    public static ProjectResource toResourceFromEntity(Project entity){
        //varios if con validaciones para hacer un return
        if(entity.getState()== ProjectState.LOOKING_FOR_DEVELOPERS){
            /*
             * Get Project Resource where State is "En busqueda"
             * Returns ProjectResource
             */
            return new ProjectResource(entity.getId(),entity.getName(),entity.getDescription(),
                    entity.getState().name(), entity.getProgress(),entity.getEnterprise().getId(),entity.getCandidates(),
                    entity.getLanguages(),entity.getFrameworks(),entity.getType().name(),
                    entity.getBudget(),entity.getMethodologies());
        }

        if(entity.getState()== ProjectState.IN_PROCESS){
            return new ProjectResource(entity.getId(),entity.getName(),entity.getDescription(),
                    entity.getState().name(), entity.getProgress(),entity.getEnterprise().getId(),
                    entity.getDeveloper().getId(),entity.getCandidates(),
                    entity.getLanguages(),entity.getFrameworks(),entity.getType().name(),
                    entity.getBudget(),entity.getMethodologies());
        }

        if (entity.getState() == ProjectState.COMPLETED) {
            return new ProjectResource(entity.getId(), entity.getName(), entity.getDescription(),
                    entity.getState().name(), entity.getProgress(), entity.getEnterprise().getId(),
                    entity.getDeveloper().getId(), entity.getCandidates(),
                    entity.getLanguages(), entity.getFrameworks(), entity.getType().name(),
                    entity.getBudget(), entity.getMethodologies());
        }

        throw new IllegalArgumentException("The state of the project is not valid");
    }
}
