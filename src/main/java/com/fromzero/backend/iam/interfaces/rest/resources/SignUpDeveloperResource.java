package com.fromzero.backend.iam.interfaces.rest.resources;


public record SignUpDeveloperResource(
        String username,
        String password,
        String firstName,
        String lastName
) {
}