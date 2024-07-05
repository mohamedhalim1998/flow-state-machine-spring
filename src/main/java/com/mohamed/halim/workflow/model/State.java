package com.mohamed.halim.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class State implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private boolean initial;
    @Column(name = "end_state")
    private boolean end;
    @OneToMany(mappedBy = "from")
    @JsonIgnore
    private List<Transition> transitions;

    public State(String name, boolean initial, boolean end) {
        this.name = name;
        this.initial = initial;
        this.end = end;
    }
    public State(String name) {
        this.name = name;
    }
}
