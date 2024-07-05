package com.mohamed.halim.workflow.repo;

import com.mohamed.halim.workflow.model.Transition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransitionRepository extends JpaRepository<Transition, Long> {
}
