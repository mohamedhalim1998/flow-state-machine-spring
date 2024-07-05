package com.mohamed.halim.workflow.service;

import com.mohamed.halim.workflow.config.FlowStateConfig;
import com.mohamed.halim.workflow.model.State;
import com.mohamed.halim.workflow.model.Ticket;
import com.mohamed.halim.workflow.model.Transition;
import com.mohamed.halim.workflow.repo.EventRepository;
import com.mohamed.halim.workflow.repo.TicketRepository;
import lombok.SneakyThrows;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TicketService extends CrudService<Ticket> {
    private final TicketRepository repository;
    private final FlowStateConfig flowStateConfig;
    private final EventRepository eventRepository;
    public TicketService(TicketRepository repository, FlowStateConfig flowStateConfig, EventRepository eventRepository) {
        super(repository);
        this.repository = repository;
        this.flowStateConfig = flowStateConfig;
        this.eventRepository = eventRepository;
    }

    public List<Transition> getTransitions(Long id) {
        var ticket = repository.findById(id);
        if(ticket.isPresent()) {
            return ticket.get().getCurrState().getTransitions();
        }
        return List.of();
    }
    @SneakyThrows
    public State sendEvent(Long id, Long eventId) {
        var machine = flowStateConfig.flowMachine();
        var ticket = repository.findById(id);
        if(ticket.isPresent()) {
            machine.getStateMachineAccessor()
                    .doWithAllRegions(access -> access.resetStateMachine(new DefaultStateMachineContext<>(ticket.get().getCurrState(), null, null, null)));
            machine.start();
            var event = eventRepository.findById(eventId);
            machine.sendEvent(event.get());
            var state = machine.getState().getId();
            ticket.get().setCurrState(state);
            repository.save(ticket.get());
            return state;
        }
        return null;
    }
}
