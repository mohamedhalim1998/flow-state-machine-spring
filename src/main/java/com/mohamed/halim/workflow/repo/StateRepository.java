package com.mohamed.halim.workflow.repo;

import com.mohamed.halim.workflow.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
