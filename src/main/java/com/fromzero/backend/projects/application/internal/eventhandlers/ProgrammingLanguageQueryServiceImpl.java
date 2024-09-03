package com.fromzero.backend.projects.application.internal.eventhandlers;

import com.fromzero.backend.projects.domain.model.aggregates.ProgrammingLanguage;
import com.fromzero.backend.projects.domain.model.queries.GetProgrammingLanguageByIdQuery;
import com.fromzero.backend.projects.domain.services.ProgrammingLanguagesQueryService;
import com.fromzero.backend.projects.infrastructure.persistence.jpa.repositories.ProgrammingLanguagesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProgrammingLanguageQueryServiceImpl implements ProgrammingLanguagesQueryService {
    private final ProgrammingLanguagesRepository programmingLanguagesRepository;

    public ProgrammingLanguageQueryServiceImpl(ProgrammingLanguagesRepository programmingLanguagesRepository) {
        this.programmingLanguagesRepository = programmingLanguagesRepository;
    }

    @Override
    public Optional<ProgrammingLanguage> handle(GetProgrammingLanguageByIdQuery query) {
        return this.programmingLanguagesRepository.findProgrammingLanguageById(query.id());
    }
}
