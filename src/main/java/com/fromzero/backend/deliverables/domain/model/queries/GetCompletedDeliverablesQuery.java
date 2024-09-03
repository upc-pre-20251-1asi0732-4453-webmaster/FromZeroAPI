package com.fromzero.backend.deliverables.domain.model.queries;


import com.fromzero.backend.deliverables.domain.model.aggregates.Deliverable;

import java.util.List;

public record GetCompletedDeliverablesQuery(List<Deliverable> deliverables) {
}
