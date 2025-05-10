package com.fromzero.backend.user.interfaces.rest;

import com.fromzero.backend.iam.domain.model.aggregates.User;
import com.fromzero.backend.iam.domain.model.entities.Role;
import com.fromzero.backend.iam.domain.model.valueobjects.Roles;
import com.fromzero.backend.user.domain.model.aggregates.Enterprise;
import com.fromzero.backend.user.domain.model.commands.UpdateEnterpriseCommand;
import com.fromzero.backend.user.domain.model.queries.GetAllEnterprisesAsyncQuery;
import com.fromzero.backend.user.domain.model.queries.GetEnterpriseByIdQuery;
import com.fromzero.backend.user.domain.model.queries.GetEnterpriseByUserIdAsyncQuery;
import com.fromzero.backend.user.domain.services.EnterpriseCommandService;
import com.fromzero.backend.user.domain.services.EnterpriseQueryService;
import com.fromzero.backend.user.interfaces.rest.resources.UpdateEnterpriseResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class EnterpriseControllerTest {

    @Mock
    private EnterpriseQueryService enterpriseQueryService;

    @Mock
    private EnterpriseCommandService enterpriseCommandService;

    @InjectMocks
    private EnterpriseController enterpriseController;

    private Enterprise enterprise;
    private UpdateEnterpriseResource enterpriseResource;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Role role = new Role(Roles.ROLE_ENTERPRISE);

        // Crear el objeto User
        user = new User();
        user.setId(1L);
        user.setUsername("john.doe");
        user.setPassword("password");
        user.addRole(role);

        // Crear el objeto Enterprise
        enterprise = new Enterprise();
        enterprise.setId(1L);
        enterprise.setUser(user);
        enterprise.setEnterpriseName("Papus Inc.");
        enterprise.setDescription("No description provided.");
        enterprise.setCountry("Afganistan");
        enterprise.setRuc("No RUC provided.");
        enterprise.setPhone("103958371");
        enterprise.setWebsite("Papus.org");
        enterprise.setProfileImgUrl("https://cdn-icons-png.flaticon.com/512/3237/3237472.png");
        enterprise.setSector("No sector provided.");

        enterpriseResource = new UpdateEnterpriseResource(
                enterprise.getEnterpriseName(),
                enterprise.getDescription(),
                enterprise.getCountry(),
                enterprise.getRuc(),
                enterprise.getPhone(),
                enterprise.getWebsite(),
                enterprise.getProfileImgUrl(),
                enterprise.getSector()
        );


    }

    @Test
    void getAllEnterprises() {
        when(enterpriseQueryService.handle(any(GetAllEnterprisesAsyncQuery.class))).thenReturn(List.of(enterprise));
        assertNotNull(enterpriseController.getAllEnterprises());
    }

    @Test
    void getEnterpriseById() {
        when(enterpriseQueryService.handle(any(GetEnterpriseByIdQuery.class))).thenReturn(Optional.of(enterprise));
        assertNotNull(enterpriseController.getEnterpriseById(enterprise.getId()));
    }

    @Test
    void getEnterpriseByUserId() {
        when(enterpriseQueryService.handle(any(GetEnterpriseByUserIdAsyncQuery.class))).thenReturn(Optional.of(enterprise));
        assertNotNull(enterpriseController.getEnterpriseByUserId(user.getId()));
    }

    @Test
    void updateEnterprise() {
        when(enterpriseCommandService.handle(any(UpdateEnterpriseCommand.class))).thenReturn(Optional.of(enterprise));
        enterpriseController.updateEnterprise(enterprise.getId(), enterpriseResource);
        verify(enterpriseCommandService, times(1)).handle(any(UpdateEnterpriseCommand.class));
    }
}