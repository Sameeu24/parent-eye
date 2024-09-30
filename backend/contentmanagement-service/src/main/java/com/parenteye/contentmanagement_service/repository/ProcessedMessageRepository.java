package com.parenteye.contentmanagement_service.repository;


import com.parenteye.contentmanagement_service.domian.ProcessedMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedMessageRepository extends MongoRepository<ProcessedMessage,String> {

    List<ProcessedMessage> findByStatus(String status);
}
