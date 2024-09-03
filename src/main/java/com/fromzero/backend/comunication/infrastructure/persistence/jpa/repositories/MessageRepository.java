package com.fromzero.backend.comunication.infrastructure.persistence.jpa.repositories;


import com.fromzero.backend.comunication.domain.model.aggregates.Message;
import com.fromzero.backend.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByRecipient(User recipient);

}
