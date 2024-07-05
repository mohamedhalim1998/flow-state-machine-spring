package com.mohamed.halim.workflow.repo;

import com.mohamed.halim.workflow.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
