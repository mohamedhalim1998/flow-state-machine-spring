package com.mohamed.halim.workflow.controller;

import com.mohamed.halim.workflow.model.State;
import com.mohamed.halim.workflow.model.Ticket;
import com.mohamed.halim.workflow.model.Transition;
import com.mohamed.halim.workflow.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController extends CrudController<Ticket> {
    private final TicketService ticketService;
    public TicketController(TicketService service) {
        super(service);
        this.ticketService = service;
    }
    @GetMapping("/{id}/transitions")
    public List<Transition> getTransitions(@PathVariable("id") Long id) {
        return ticketService.getTransitions(id);
    }
    @PostMapping("/{id}/event")
    public State sendEvent(@PathVariable("id") Long id, @RequestParam("id") Long eventId) {
        return ticketService.sendEvent(id, eventId);
    }
}
