package com.fromzero.backend.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpEnterpriseResource(
        String username,
        String password,
        String enterpriseName
        ) {
}