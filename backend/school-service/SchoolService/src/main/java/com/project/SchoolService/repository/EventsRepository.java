package com.project.SchoolService.repository;

import com.project.SchoolService.domain.Events;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events, Long> {
}
