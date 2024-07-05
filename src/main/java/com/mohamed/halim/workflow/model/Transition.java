package com.mohamed.halim.workflow.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"from", "to"})
public class Transition implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "from_id", nullable = false)
    private State from;
    @ManyToOne
    @JoinColumn(name = "to_id", nullable = false)
    private State to;
    @OneToOne(cascade = CascadeType.ALL)
    private Event event;

    public Transition(State from, State to, String event) {
        this.from = from;
        this.to = to;
        this.event = new Event();
        this.event.setName(event);
    }
}
