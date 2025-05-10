package com.fromzero.backend.user.interfaces.rest;

import com.fromzero.backend.iam.domain.model.aggregates.User;
import com.fromzero.backend.iam.domain.model.valueobjects.Roles;
import com.fromzero.backend.iam.domain.model.entities.Role;
import com.fromzero.backend.user.domain.model.aggregates.Developer;
import com.fromzero.backend.user.domain.model.queries.GetAllDevelopersAsyncQuery;
import com.fromzero.backend.user.domain.services.DeveloperCommandService;
import com.fromzero.backend.user.domain.services.DeveloperQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
class DeveloperControllerTest {

    @Mock
    private DeveloperQueryService developerQueryService;

    @Mock
    private DeveloperCommandService developerCommandService;

    @InjectMocks
    private DeveloperController developerController;

    private Developer developer;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Role role = new Role(Roles.ROLE_DEVELOPER);

        // Crear el objeto User
        user = new User();
        user.setId(1L);
        user.setUsername("john.doe");
        user.setPassword("password");
        user.addRole(role);

        // Crear el objeto Developer asociado al User
        developer = new Developer();
        developer.setId(1L);
        developer.setUser(user);
        developer.setFirstName("John");
        developer.setLastName("Doe");
        developer.setDescription("Description");
        developer.setCountry("Country");
        developer.setPhone("1234567890");
        developer.setCompletedProjects(5);
        developer.setSpecialties("Java, Python");
        developer.setProfileImgUrl("profileImgUrl");
    }

    @Test
    void getAllDevelopers() {
        when(developerQueryService.handle(any(GetAllDevelopersAsyncQuery.class))).thenReturn(List.of(developer));
        assertNotNull(developerController.getAllDevelopers());
    }

    @Test
    void getDeveloperById() {
    }

    @Test
    void getDeveloperByUserId() {
    }

    @Test
    void updateDeveloper() {
    }
}