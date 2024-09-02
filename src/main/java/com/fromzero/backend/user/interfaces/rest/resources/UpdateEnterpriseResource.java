package com.fromzero.backend.user.interfaces.rest.resources;

public record UpdateEnterpriseResource(
        String enterpriseName,
        String description,
        String country,
        String ruc,
        String phone,
        String website,
        String profileImgUrl,
        String sector
) {
}
