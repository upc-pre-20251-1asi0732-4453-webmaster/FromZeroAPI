package com.fromzero.backend.user.domain.model.commands;

public record UpdateEnterpriseCommand(
        Long enterpriseId,
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
