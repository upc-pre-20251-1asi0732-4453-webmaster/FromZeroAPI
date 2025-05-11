package com.fromzero.backend.iam.interfaces.rest;

import com.fromzero.backend.iam.domain.model.aggregates.User;
import com.fromzero.backend.iam.domain.model.entities.Role;
import com.fromzero.backend.iam.domain.model.valueobjects.Roles;
import com.fromzero.backend.iam.domain.services.UserCommandService;
import com.fromzero.backend.iam.domain.model.commands.SignUpDeveloperCommand;
import com.fromzero.backend.iam.domain.model.commands.SignUpEnterpriseCommand;
import com.fromzero.backend.iam.domain.model.commands.SignInCommand;
import com.fromzero.backend.iam.domain.model.commands.SignUpCommand;
import com.fromzero.backend.iam.interfaces.rest.resources.SignInResource;
import com.fromzero.backend.iam.interfaces.rest.resources.SignUpDeveloperResource;
import com.fromzero.backend.iam.interfaces.rest.resources.SignUpEnterpriseResource;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private UserCommandService userCommandService;

    @InjectMocks
    private AuthenticationController authenticationController;

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
    void signIn() {
        when(userCommandService.handle(any(SignInCommand.class)))
                .thenReturn(Optional.of(new ImmutablePair<>(user, "token")));
        assertNotNull(authenticationController.signIn(new SignInResource("john.doe", "password")));
    }

    @Test
    void signUpDeveloper() {
        when(userCommandService.handle(any(SignUpDeveloperCommand.class))).thenReturn(Optional.of(user));
        assertNotNull(authenticationController.signUpDeveloper(new SignUpDeveloperResource("john.doe", "password", "John", "Doe")));
    }

    @Test
    void signUpEnterprise() {
        when(userCommandService.handle(any(SignUpEnterpriseCommand.class))).thenReturn(Optional.of(user));
        assertNotNull(authenticationController.signUpEnterprise(new SignUpEnterpriseResource("john.doe", "password", "John")));
    }
}