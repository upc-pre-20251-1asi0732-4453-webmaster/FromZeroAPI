package com.fromzero.backend.projects.interfaces.acl;


import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.projects.domain.model.commands.UpdateProjectProgressCommand;
import com.fromzero.backend.projects.domain.model.queries.GetAllProjectsByStateQuery;
import com.fromzero.backend.projects.domain.model.queries.GetAllProjectsQuery;
import com.fromzero.backend.projects.domain.model.queries.GetProjectByIdQuery;
import com.fromzero.backend.projects.domain.services.ProjectCommandService;
import com.fromzero.backend.projects.domain.services.ProjectQueryService;
import com.fromzero.backend.projects.domain.valueobjects.ProjectState;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectContextFacade {
    private final ProjectQueryService projectQueryService;
    private final ProjectCommandService projectCommandService;

    public ProjectContextFacade(ProjectQueryService projectQueryService,
                                ProjectCommandService projectCommandService) {
        this.projectQueryService = projectQueryService;
        this.projectCommandService = projectCommandService;
    }

    /*
     * Se espera que el contexto que utilize este metodo
     * haga un resource el cual manipule o haga uso de
     * algunos atributos de la entidad Project que se
     * está devolviendo
     * */
    public List<Project> getAllProjects(){
        var getAllProjectsQuery = new GetAllProjectsQuery();
        return this.projectQueryService.handle(getAllProjectsQuery);
    }

    public List<Project> getAllProjectsByState(ProjectState state){
        try{
            var getAllProjectsByStateQuery = new GetAllProjectsByStateQuery(state);
            return this.projectQueryService.handle(getAllProjectsByStateQuery);
        }catch (IllegalArgumentException e){
            return Collections.emptyList();
        }
    }

    public Project getProjectById(Long id){
        var getProjectByIdQuery = new GetProjectByIdQuery(id);
        var project = this.projectQueryService.handle(getProjectByIdQuery);
        return project.orElse(null);
    }

    public Project updateProjectProgress(Long projectId,Long completedDeliverables, Integer totalDeliverables){
        double percentComplete = (double) completedDeliverables / totalDeliverables * 100;
        var getProjectByIdQuery = new GetProjectByIdQuery(projectId);
        try {
            var project = this.projectQueryService.handle(getProjectByIdQuery);
            if(project.isEmpty())throw new IllegalArgumentException();
            var updateProjectProgress = new UpdateProjectProgressCommand(project.get(),percentComplete);
            var updatedProject = this.projectCommandService.handle(updateProjectProgress);
            if(updatedProject.isEmpty()) throw new IllegalArgumentException();
            return updatedProject.get();
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
