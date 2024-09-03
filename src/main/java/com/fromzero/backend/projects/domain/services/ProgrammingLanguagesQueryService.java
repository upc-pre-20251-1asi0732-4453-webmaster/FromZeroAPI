package com.fromzero.backend.projects.domain.services;


import com.fromzero.backend.projects.domain.model.aggregates.ProgrammingLanguage;
import com.fromzero.backend.projects.domain.model.queries.GetProgrammingLanguageByIdQuery;

import java.util.Optional;

public interface ProgrammingLanguagesQueryService {
    Optional<ProgrammingLanguage> handle(GetProgrammingLanguageByIdQuery query);
}
