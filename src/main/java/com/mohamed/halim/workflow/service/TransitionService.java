package com.mohamed.halim.workflow.service;

import com.mohamed.halim.workflow.model.Transition;
import com.mohamed.halim.workflow.repo.TransitionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransitionService extends CrudService<Transition> {
    public TransitionService(TransitionRepository repository) {
        super(repository);
    }
}
