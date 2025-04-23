package com.fromzero.backend.deliverables.application.internal.commandservices;

import com.fromzero.backend.deliverables.domain.exceptions.DeliverableAlreadyApprovedException;
import com.fromzero.backend.deliverables.domain.exceptions.DeliverableWithoutUploadException;
import com.fromzero.backend.deliverables.domain.exceptions.IllegalDeliverableStateException;
import com.fromzero.backend.deliverables.domain.model.aggregates.Deliverable;
import com.fromzero.backend.deliverables.domain.model.commands.CreateDeliverableCommand;
import com.fromzero.backend.deliverables.domain.model.commands.UpdateDeliverableCommand;
import com.fromzero.backend.deliverables.domain.model.commands.UpdateDeliverableStatusCommand;
import com.fromzero.backend.deliverables.domain.model.commands.UpdateDeveloperMessageCommand;
import com.fromzero.backend.deliverables.domain.services.DeliverableCommandService;
import com.fromzero.backend.deliverables.domain.valueobjects.DeliverableStatus;
import com.fromzero.backend.deliverables.infrastructure.persistence.jpa.repositories.DeliverableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliverableCommandServiceImpl implements DeliverableCommandService {
    private final DeliverableRepository deliverableRepository;
    public DeliverableCommandServiceImpl(DeliverableRepository deliverableRepository) {
        this.deliverableRepository = deliverableRepository;
    }

    @Override
    public Optional<Deliverable> handle(CreateDeliverableCommand command) {
        var deliverable = new Deliverable(command);
        this.deliverableRepository.save(deliverable);

        return Optional.of(deliverable);
    }

    @Override
    public void handle(List<CreateDeliverableCommand> commands) {
        //List<Deliverable> deliverablesList  = new ArrayList<>();
        commands.forEach(command -> {
            Optional<Deliverable> deliverable = this.handle(command);
            if(deliverable.isEmpty())throw new IllegalArgumentException();
            this.deliverableRepository.save(deliverable.get());
        });

    }

    @Override
    public Optional<Deliverable> handle(UpdateDeveloperMessageCommand command) {
        try {
            var deliverable = this.deliverableRepository.findById(command.deliverableId());
            if(deliverable.isEmpty())throw new IllegalArgumentException();
            deliverable.get().setDeveloperMessage(command.message());
            deliverable.get().setState(DeliverableStatus.WAITING);
            this.deliverableRepository.save(deliverable.get());
            return deliverable;
        }catch (IllegalArgumentException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Deliverable> handle(UpdateDeliverableStatusCommand command) {
        var deliverable = this.deliverableRepository.findById(command.deliverableId());
        if(deliverable.isEmpty()){
            throw new IllegalArgumentException();
        }

        //if there's no files/developerMessage from the developer
        if(deliverable.get().getDeveloperMessage()==null){
            throw new DeliverableWithoutUploadException("There's no upload from the developer");
        }


        //if the deliverable was approved before
        if (deliverable.get().getState() == DeliverableStatus.APPROVED){
            throw new DeliverableAlreadyApprovedException("Deliverable is already approved");
        }


        //if the status is not WAITING
        if (deliverable.get().getState() != DeliverableStatus.WAITING) {
            throw new IllegalDeliverableStateException("The state must be WAITING");
        }

        if (command.accepted()){
            deliverable.get().setState(DeliverableStatus.APPROVED);
            //System.out.println("El proyecto es: "+deliverable.get().getProject().getProgress().toString());
        }else deliverable.get().setState(DeliverableStatus.REJECTED);
        this.deliverableRepository.save(deliverable.get());
        return deliverable;



    }
    @Override
    public Optional<Deliverable> handle(UpdateDeliverableCommand command) {
        Optional<Deliverable> deliverableOptional = deliverableRepository.findById(command.deliverableId());
        if (deliverableOptional.isPresent()) {
            Deliverable deliverable = deliverableOptional.get();
            deliverable.setName(command.name());
            deliverable.setDescription(command.description());
            deliverable.setDate(String.valueOf(command.date()));
            deliverableRepository.save(deliverable);
            return Optional.of(deliverable);
        }
        return Optional.empty();
    }

}

