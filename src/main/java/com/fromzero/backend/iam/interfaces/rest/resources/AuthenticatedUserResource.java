package com.fromzero.backend.iam.interfaces.rest.resources;

import java.util.List;

public record AuthenticatedUserResource(Long id, String username, List<String> roles, String token) {

}
