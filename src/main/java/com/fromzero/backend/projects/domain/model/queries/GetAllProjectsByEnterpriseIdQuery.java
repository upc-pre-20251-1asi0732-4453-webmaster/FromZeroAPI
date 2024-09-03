package com.fromzero.backend.projects.domain.model.queries;


import com.fromzero.backend.user.domain.model.aggregates.Enterprise;

public record GetAllProjectsByEnterpriseIdQuery(Enterprise enterprise) {
}
