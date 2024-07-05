package com.mohamed.halim.workflow.repo;


import com.mohamed.halim.workflow.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
