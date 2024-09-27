package com.project.screentime_service.repository;

import com.project.screentime_service.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Long> {
}
