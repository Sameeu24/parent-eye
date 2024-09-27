package com.parenteye.contentmanagement_service.repository;


import com.parenteye.contentmanagement_service.domian.ProcessedMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedMessageRepository extends MongoRepository<ProcessedMessage,String> {
}
