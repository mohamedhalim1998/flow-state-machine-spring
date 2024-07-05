package com.mohamed.halim.workflow.controller;

import com.mohamed.halim.workflow.model.State;
import com.mohamed.halim.workflow.service.StateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/state")
public class StateController extends CrudController<State>{
    private final StateService service;

    public StateController(StateService service) {
        super(service);
        this.service = service;
    }

}
