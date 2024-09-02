package com.fromzero.backend.user.interfaces.rest.resources;

public record DeveloperResource(
        Long id,
        String firstName,
        String lastName,
        String description,
        String country,
        String phone,
        String specialties,
        String profileImgUrl,
        Long userId
) {
}
