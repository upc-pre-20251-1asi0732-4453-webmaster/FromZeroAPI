package com.fromzero.backend.iam.interfaces.rest;


import com.fromzero.backend.iam.domain.model.entities.Role;
import com.fromzero.backend.iam.domain.model.queries.GetRoleByNameQuery;
import com.fromzero.backend.iam.domain.model.queries.GetAllRolesQuery;
import com.fromzero.backend.iam.domain.model.commands.SeedRolesCommand;
import com.fromzero.backend.iam.domain.model.valueobjects.Roles;
import com.fromzero.backend.iam.domain.services.RoleCommandService;
import com.fromzero.backend.iam.domain.services.RoleQueryService;
import com.fromzero.backend.iam.interfaces.rest.resources.RoleResource;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class RolesControllerTest {

    @Mock
    private RoleQueryService roleQueryService;

    @InjectMocks
    private RolesController rolesController;

    private Role role;
    private RoleCommandService roleCommandService;
    private RoleResource roleResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        role = new Role(Roles.ROLE_DEVELOPER);
        roleResource= new RoleResource(1L, Roles.ROLE_DEVELOPER.name());
    }

    @Test
    void getAllRoles() {
        when(roleQueryService.handle(any(GetAllRolesQuery.class))).thenReturn(List.of(role));
        assertNotNull(rolesController.getAllRoles());
    }
}