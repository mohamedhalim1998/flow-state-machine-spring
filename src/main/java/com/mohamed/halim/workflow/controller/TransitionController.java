package com.mohamed.halim.workflow.controller;

import com.mohamed.halim.workflow.model.Transition;
import com.mohamed.halim.workflow.service.TransitionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transition")
public class TransitionController extends CrudController<Transition> {

    public TransitionController(TransitionService service) {
        super(service);
    }
}
