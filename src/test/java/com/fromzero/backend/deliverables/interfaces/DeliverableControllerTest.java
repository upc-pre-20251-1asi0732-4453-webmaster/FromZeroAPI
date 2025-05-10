package com.fromzero.backend.deliverables.interfaces;

import com.fromzero.backend.deliverables.domain.model.commands.*;
import com.fromzero.backend.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.backend.deliverables.domain.model.queries.GetAllDeliverablesByProjectIdQuery;
import com.fromzero.backend.deliverables.domain.model.queries.GetDeliverableByIdQuery;
import com.fromzero.backend.deliverables.domain.services.DeliverableCommandService;
import com.fromzero.backend.deliverables.domain.services.DeliverableQueryService;
import com.fromzero.backend.deliverables.domain.valueobjects.DeliverableStatus;
import com.fromzero.backend.deliverables.interfaces.rest.resources.CreateDeliverableResource;
import com.fromzero.backend.deliverables.interfaces.rest.resources.UpdateDeliverableResource;
import com.fromzero.backend.projects.interfaces.acl.ProjectContextFacade;
import com.fromzero.backend.user.interfaces.acl.ProfileContextFacade;
import com.fromzero.backend.iam.domain.model.entities.Role;
import com.fromzero.backend.iam.domain.model.valueobjects.Roles;
import com.fromzero.backend.iam.domain.model.aggregates.User;
import com.fromzero.backend.projects.domain.model.aggregates.Framework;
import com.fromzero.backend.projects.domain.model.aggregates.ProgrammingLanguage;
import com.fromzero.backend.projects.domain.model.aggregates.Project;
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

import javax.swing.text.html.Option;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class DeliverableControllerTest {

    @Mock
    private ProjectContextFacade projectContextFacade;

    @Mock
    private ProfileContextFacade profileContextFacade;

    @Mock
    private DeliverableQueryService deliverableQueryService;

    @Mock
    private DeliverableCommandService deliverableCommandService;

    @InjectMocks
    private DeliverableController deliverableController;

    private ProgrammingLanguage programmingLanguage;
    private List<Long> ListElement;
    private Framework framework;
    private Project project;

    private AssignProjectDeveloperResource assignProjectDeveloperResource;
    private CreateProjectResource createProjectResource;
    private UpdateProjectCandidatesListResource updateProjectCandidatesListResource;

    private Deliverable deliverable;
    private CreateDeliverableResource createDeliverableResource;
    private UpdateDeliverableResource updateDeliverableResource;
    private Developer developer;
    private Enterprise enterprise;
    private User userEnterprise;
    private User userDev;

    private UpdateDeveloperDescriptionCommand updateDeveloperDescriptionCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(profileContextFacade.getEnterpriseByUserId(anyLong()))
                .thenReturn(enterprise);
        when(projectContextFacade.getProjectById(anyLong()))
                .thenReturn(project);

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

        deliverable = new Deliverable();
        deliverable.setId(1L);
        deliverable.setName("Deliverable A");
        deliverable.setDescription("Description of Deliverable A");
        deliverable.setDeadline(LocalDateTime.now());
        deliverable.setState(DeliverableStatus.PENDING);
        deliverable.setFileUrl("https://example.com/deliverable-a");
        deliverable.setProject(project);
        deliverable.setOrderNumber(1);

        createDeliverableResource = new CreateDeliverableResource(
                deliverable.getName(),
                deliverable.getDescription(),
                deliverable.getDeadline().toString(),
                deliverable.getProject().getId()
        );

        updateDeliverableResource = new UpdateDeliverableResource(
                deliverable.getName(),
                deliverable.getDescription(),
                deliverable.getDeadline().toString()
        );

    }

    @Test
    void createDeliverable() {
        when(deliverableCommandService.handle(any(CreateDeliverableCommand.class))).thenReturn(Optional.of(deliverable));
        assertNotNull(deliverableController.createDeliverable(createDeliverableResource));
    }

    @Test
    void getAllDeliverablesByProjectId() {
        when(deliverableQueryService.handle(any(GetAllDeliverablesByProjectIdQuery.class)))
                .thenReturn(List.of(deliverable));
        assertNotNull(deliverableController.getAllDeliverablesByProjectId(project.getId()));
    }

    @Test
    void getDeliverableById() {
        when(deliverableQueryService.handle(any(GetDeliverableByIdQuery.class)))
                .thenReturn(Optional.of(deliverable));
        assertNotNull(deliverableController.getDeliverableById(deliverable.getId()));
    }

    @Test
    void sendDeliverable() {
        when(deliverableCommandService.handle(any(UpdateDeveloperDescriptionCommand.class))).thenReturn(Optional.of(deliverable));
        assertNotNull(deliverableController.sendDeliverable(deliverable.getId(), project.getId(), "New Message"));
    }

    @Test
    void reviewDeliverable() {
        when(deliverableCommandService.handle(any(UpdateDeliverableStatusCommand.class))).thenReturn(Optional.of(deliverable));
        assertNotNull(deliverableController.reviewDeliverable(deliverable.getId(), true, project.getId().toString()));
    }

    @Test
    void updateDeliverable() {
        when(deliverableCommandService.handle(any(UpdateDeliverableCommand.class))).thenReturn(Optional.of(deliverable));
        assertNotNull(deliverableController.updateDeliverable(deliverable.getId(), updateDeliverableResource, project.getId().toString()));
    }

    @Test
    void deleteDeliverable() {
    doNothing().when(deliverableCommandService).handle(any(DeleteDeliverableCommand.class));
    assertNotNull(deliverableController.deleteDeliverable(deliverable.getProject().getId(), deliverable.getId()));

    }
}