package com.fromzero.backend.user.application.internal.commandservices;

import com.fromzero.backend.user.domain.model.aggregates.Enterprise;
import com.fromzero.backend.user.domain.model.commands.UpdateEnterpriseCommand;
import com.fromzero.backend.user.domain.services.EnterpriseCommandService;
import com.fromzero.backend.user.infrastructure.persistence.jpa.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnterpriseCommandServiceImpl implements EnterpriseCommandService {

    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseCommandServiceImpl(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }


    @Override
    public Optional<Enterprise> handle(UpdateEnterpriseCommand command) {
        var enterprise= enterpriseRepository.findEnterpriseByUser_Id(command.enterpriseId())
                .orElseThrow(() -> new IllegalArgumentException("Enterprise with id " + command.enterpriseId() + " not found"));
        enterprise.setDescription(command.description());
        enterprise.setCountry(command.country());
        enterprise.setRuc(command.ruc());
        enterprise.setPhone(command.phone());
        enterprise.setWebsite(command.website());
        enterprise.setProfileImgUrl(command.profileImgUrl());
        enterprise.setSector(command.sector());

        enterpriseRepository.save(enterprise);

        return Optional.of(enterprise);
    }
}
