package com.project.screentime_service.repository;

import com.project.screentime_service.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LinkRepository extends MongoRepository<Link,String> {
}
