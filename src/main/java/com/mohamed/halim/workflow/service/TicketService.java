package com.mohamed.halim.workflow.service;

import com.mohamed.halim.workflow.model.Ticket;
import com.mohamed.halim.workflow.repo.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService extends CrudService<Ticket> {
    public TicketService(TicketRepository repository) {
        super(repository);
    }
}
