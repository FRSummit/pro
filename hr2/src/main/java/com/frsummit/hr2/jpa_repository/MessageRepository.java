package com.frsummit.hr2.jpa_repository;

import com.frsummit.hr2.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("messageRepository")
public interface MessageRepository extends CrudRepository<Message, Long> {
}
