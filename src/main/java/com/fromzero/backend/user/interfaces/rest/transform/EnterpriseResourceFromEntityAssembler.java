package com.fromzero.backend.user.interfaces.rest.transform;

import com.fromzero.backend.user.domain.model.aggregates.Enterprise;
import com.fromzero.backend.user.interfaces.rest.resources.EnterpriseResource;

public class EnterpriseResourceFromEntityAssembler {
    public static EnterpriseResource toResourceFromEntity(Enterprise entity) {
        return new EnterpriseResource(
                entity.getId(),
                entity.getEnterpriseName(),
                entity.getDescription(),
                entity.getCountry(),
                entity.getRuc(),
                entity.getPhone(),
                entity.getWebsite(),
                entity.getProfileImgUrl(),
                entity.getSector(),
                entity.getUser().getId());
    }
}
