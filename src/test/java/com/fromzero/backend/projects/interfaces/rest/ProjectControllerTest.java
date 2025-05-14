package com.fromzero.backend.projects.interfaces.rest;

import com.fromzero.backend.iam.domain.model.entities.Role;
import com.fromzero.backend.iam.domain.model.valueobjects.Roles;
import com.fromzero.backend.iam.domain.model.aggregates.User;
import com.fromzero.backend.projects.domain.model.aggregates.Framework;
import com.fromzero.backend.projects.domain.model.aggregates.ProgrammingLanguage;
import com.fromzero.backend.projects.domain.model.aggregates.Project;
import com.fromzero.backend.projects.domain.model.commands.AssignProjectDeveloperCommand;
import com.fromzero.backend.projects.domain.model.commands.CreateProjectCommand;
import com.fromzero.backend.projects.domain.model.commands.DeleteProjectCommand;
import com.fromzero.backend.projects.domain.model.commands.UpdateProjectCandidatesListCommand;
import com.fromzero.backend.projects.domain.model.queries.*;
import com.fromzero.backend.projects.domain.services.FrameworksQueryService;
import com.fromzero.backend.projects.domain.services.ProgrammingLanguagesQueryService;
import com.fromzero.backend.projects.domain.services.ProjectCommandService;
import com.fromzero.backend.projects.domain.services.ProjectQueryService;
import com.fromzero.backend.projects.domain.valueobjects.ProjectState;
import com.fromzero.backend.projects.domain.valueobjects.ProjectType;
import com.fromzero.backend.projects.interfaces.rest.resources.AssignProjectDeveloperResource;
import com.fromzero.backend.projects.interfaces.rest.resources.CreateProjectResource;
import com.fromzero.backend.projects.interfaces.rest.resources.UpdateProjectCandidatesListResource;
import com.fromzero.backend.user.domain.model.aggregates.Developer;
import com.fromzero.backend.user.domain.model.aggregates.Enterprise;
import com.fromzero.backend.user.interfaces.acl.ProfileContextFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ProjectControllerTest {

    @Mock
    private ProfileContextFacade profileContextFacade;

    @Mock
    private ProjectQueryService projectQueryService;
    @Mock
    private FrameworksQueryService frameworksQueryService;
    @Mock
    private ProgrammingLanguagesQueryService programmingLanguagesQueryService;

    @Mock
    private ProjectCommandService projectCommandService;

    @InjectMocks
    private ProjectController projectController;

    private ProgrammingLanguage programmingLanguage;
    private List<Long> ListElement;
    private Framework framework;
    private Project project;

    private AssignProjectDeveloperResource assignProjectDeveloperResource;
    private CreateProjectResource createProjectResource;
    private UpdateProjectCandidatesListResource updateProjectCandidatesListResource;

    private Developer developer;
    private Enterprise enterprise;
    private User userEnterprise;
    private User userDev;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        when(profileContextFacade.getEnterpriseByUserId(anyLong()))
                .thenReturn(enterprise);

        programmingLanguage = new ProgrammingLanguage();
        programmingLanguage.setId(1L);
        programmingLanguage.setName("Java");
        ListElement = Arrays.asList(1L);

        framework = new Framework();
        framework.setId(1L);
        framework.setName("Spring");


        Role role = new Role(Roles.ROLE_DEVELOPER);


        userDev = new User();
        userDev.setId(1L);
        userDev.setUsername("john.doe");
        userDev.setPassword("password");
        userDev.addRole(role);

        developer = new Developer();
        developer.setId(1L);
        developer.setUser(userDev);
        developer.setFirstName("John");
        developer.setLastName("Doe");
        developer.setDescription("Description");
        developer.setCountry("Country");
        developer.setPhone("1234567890");
        developer.setCompletedProjects(5);
        developer.setSpecialties("Java, Python");
        developer.setProfileImgUrl("profileImgUrl");

        role = new Role(Roles.ROLE_ENTERPRISE);
        enterprise = new Enterprise();
        enterprise.setId(1L);

        userEnterprise = new User();
        userEnterprise.setId(1L);
        userEnterprise.setUsername("john.doe");
        userEnterprise.setPassword("password");
        userEnterprise.addRole(role);

        // Crear el objeto Enterprise
        enterprise = new Enterprise();
        enterprise.setId(1L);
        enterprise.setUser(userEnterprise);
        enterprise.setEnterpriseName("Papus Inc.");
        enterprise.setDescription("No description provided.");
        enterprise.setCountry("Afganistan");
        enterprise.setRuc("No RUC provided.");
        enterprise.setPhone("103958371");
        enterprise.setWebsite("Papus.org");
        enterprise.setProfileImgUrl("https://cdn-icons-png.flaticon.com/512/3237/3237472.png");
        enterprise.setSector("No sector provided.");

        project = new Project();
        project.setId(1L);
        project.setName("Project A");
        project.setDescription("Description of Project A");
        project.setState(ProjectState.LOOKING_FOR_DEVELOPERS);
        project.setProgress(0.0);
        project.setType(ProjectType.MOBILE_APPLICATION);
        project.setBudget("1000");
        project.setMethodologies("Agile");
        project.setEnterprise(enterprise);

        createProjectResource = new CreateProjectResource(
                project.getName(),
                project.getDescription(),
                userEnterprise.getId(),
                ListElement,
                ListElement,
                project.getType(),
                project.getBudget(),
                project.getMethodologies()
        );
    }

    @Test
    void getProgrammingLanguages() {
        when(programmingLanguagesQueryService.handle(any(GetProgrammingLanguageByIdQuery.class))).thenReturn(Optional.of(programmingLanguage));
        assertNotNull(projectController.getProgrammingLanguages(ListElement));
    }

    @Test
    void getFrameworks() {
        when(frameworksQueryService.handle(any(GetFrameworkByIdQuery.class))).thenReturn(Optional.of(framework));
        assertNotNull(projectController.getFrameworks(ListElement));
    }

    @Test
    void createProject() {
        when(projectCommandService.handle(any(CreateProjectCommand.class))).thenReturn(Optional.of(project));
        assertNotNull(projectController.createProject(createProjectResource));
    }

    @Test
    void getAllProjects() {
        when(projectQueryService.handle(any(GetAllProjectsQuery.class))).thenReturn(List.of(project));
        assertNotNull(projectController.getAllProjects());
    }

    @Test
    void getProjectById() {
        when(projectQueryService.handle(any(GetProjectByIdQuery.class))).thenReturn(Optional.of(project));
        assertNotNull(projectController.getProjectById(project.getId()));
    }

    @Test
    void updateProjectCandidatesList() {
        when(projectQueryService.handle(any(GetProjectByIdQuery.class))).thenReturn(Optional.of(project));
        when(profileContextFacade.getDeveloperByUserId(anyLong())).thenReturn(developer);
        when(projectCommandService.handle(any(UpdateProjectCandidatesListCommand.class))).thenReturn(Optional.of(project));
        projectController.updateProjectCandidatesList(project.getId(), developer.getId());
        verify(projectCommandService, times(1)).handle(any(UpdateProjectCandidatesListCommand.class));
    }

    @Test
    void setProjectDeveloper() {

        when(projectQueryService.handle(any(GetProjectByIdQuery.class))).thenReturn(Optional.of(project));
        when(profileContextFacade.getDeveloperByUserId(anyLong())).thenReturn(developer);
        when(projectCommandService.handle(any(AssignProjectDeveloperCommand.class))).thenReturn(Optional.of(project));
        projectController.setProjectDeveloper(project.getId(), developer.getId());
        verify(projectCommandService, times(1)).handle(any(AssignProjectDeveloperCommand.class));
    }

    @Test
    void getAllProjectsByDeveloperId() {
        when(projectQueryService.handle(any(GetAllProjectsByDeveloperIdQuery.class))).thenReturn(List.of(project));
        assertNotNull(projectController.getAllProjectsByDeveloperId(developer.getId()));
    }

    @Test
    void getAllProjectsByEnterpriseId() {
        when(projectQueryService.handle(any(GetAllProjectsByEnterpriseIdQuery.class))).thenReturn(List.of(project));
        assertNotNull(projectController.getAllProjectsByEnterpriseId(developer.getId()));
    }

    @Test
    void deleteProject() {
        when(projectQueryService.handle(any(GetProjectByIdQuery.class))).thenReturn(Optional.of(project));
        assertNotNull(projectController.deleteProject(project.getId()));
    }

    @Test
    void createProjectAndGetByIdIntegration() {
        // Arrange
        when(projectCommandService.handle(any(CreateProjectCommand.class))).thenReturn(Optional.of(project));
        when(projectQueryService.handle(any(GetProjectByIdQuery.class))).thenReturn(Optional.of(project));

        // Act
        var createdProjectResponse = projectController.createProject(createProjectResource);
        var fetchedProjectResponse = projectController.getProjectById(project.getId());

        // Assert
        assertNotNull(createdProjectResponse);
        assertNotNull(fetchedProjectResponse);
        assertEquals(project.getName(), fetchedProjectResponse.getBody().name());
        assertEquals(project.getDescription(), fetchedProjectResponse.getBody().description());
        assertEquals(project.getType(), fetchedProjectResponse.getBody().type());
    }
}