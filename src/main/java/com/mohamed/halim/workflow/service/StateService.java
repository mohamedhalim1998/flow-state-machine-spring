package com.mohamed.halim.workflow.service;

import com.mohamed.halim.workflow.model.State;
import com.mohamed.halim.workflow.repo.StateRepository;
import org.springframework.stereotype.Service;

@Service
public class StateService extends CrudService<State> {
    private final StateRepository stateRepository;
    public StateService(StateRepository repository) {
        super(repository);
        this.stateRepository = repository;
    }
}
