package com.mohamed.halim.workflow.controller;

import com.mohamed.halim.workflow.model.Ticket;
import com.mohamed.halim.workflow.service.TicketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController extends CrudController<Ticket> {
    public TicketController(TicketService service) {
        super(service);
    }
}
