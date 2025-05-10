package com.fromzero.backend.iam.interfaces.rest;

import com.fromzero.backend.iam.domain.model.aggregates.User;
import com.fromzero.backend.iam.domain.model.entities.Role;
import com.fromzero.backend.iam.domain.model.valueobjects.Roles;
import com.fromzero.backend.iam.domain.services.UserQueryService;
import com.fromzero.backend.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.fromzero.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.fromzero.backend.iam.domain.model.queries.GetAllUsersQuery;
import com.fromzero.backend.iam.domain.model.queries.GetRoleByNameQuery;
import com.fromzero.backend.iam.domain.model.queries.GetUserByIdQuery;
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

class UsersControllerTest {

    @Mock
    private UserQueryService userQueryService;

    @InjectMocks
    private UsersController usersController;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Role role = new Role(Roles.ROLE_DEVELOPER);
        user = new User();
        user.setId(1L);
        user.setUsername("john.doe");
        user.setPassword("password");
        user.addRole(role);
    }

    @Test
    void getAllUsers() {
        when(userQueryService.handle(any(GetAllUsersQuery.class))).thenReturn(List.of(user));
        assertNotNull(usersController.getAllUsers());
    }

    @Test
    void getUserById() {
        when(userQueryService.handle(any(GetUserByIdQuery.class))).thenReturn(Optional.of(user));
        assertNotNull(usersController.getUserById(1L));
    }
}