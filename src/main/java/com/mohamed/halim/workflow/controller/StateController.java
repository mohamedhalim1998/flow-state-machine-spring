package com.mohamed.halim.workflow.controller;

import com.mohamed.halim.workflow.model.Event;
import com.mohamed.halim.workflow.model.State;
import com.mohamed.halim.workflow.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/state")
public class StateController extends CrudController<State>{
    private final StateService service;
    @Autowired
    @Lazy
    private StateMachine<State, Event> machine;
    public StateController(StateService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/machine")
    private StateMachine<State, Event> getMachine() {
        return machine;
    }
}
