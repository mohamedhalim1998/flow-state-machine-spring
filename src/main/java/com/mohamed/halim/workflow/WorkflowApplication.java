package com.mohamed.halim.workflow;

import com.mohamed.halim.workflow.model.Flow;
import com.mohamed.halim.workflow.model.State;
import com.mohamed.halim.workflow.model.Transition;
import com.mohamed.halim.workflow.repo.FlowRepository;
import com.mohamed.halim.workflow.repo.StateRepository;
import com.mohamed.halim.workflow.repo.TransitionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class WorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(FlowRepository flowRepository, StateRepository stateRepository, TransitionRepository transitionRepository) {
        return args -> {
            List<State> states =
                    List.of(
                            new State("created", true, false),
                            new State("brainstorming"),
                            new State("prototyping"),
                            new State("approval in progress "),
                            new State("construction in progress"),
                            new State("done"),
                            new State("released", false, true),
                            new State("cancelled", false, true)
                    );
            stateRepository.saveAll(states);
            List<Transition> transitions =
                    List.of(
                            new Transition(states.get(0), states.get(7), "cancel"),
                            new Transition(states.get(0), states.get(1), "start brain storming"),
                            new Transition(states.get(1), states.get(7), "cancel"),
                            new Transition(states.get(1), states.get(2), "start prototype"),
                            new Transition(states.get(2), states.get(7), "cancel"),
                            new Transition(states.get(2), states.get(3), "send for approval"),
                            new Transition(states.get(2), states.get(1), "start prototype"),
                            new Transition(states.get(3), states.get(7), "cancel"),
                            new Transition(states.get(3), states.get(2), "refer back"),
                            new Transition(states.get(3), states.get(1), "re-evaluate"),
                            new Transition(states.get(3), states.get(4), "start contuction"),
                            new Transition(states.get(4), states.get(5), "done"),
                            new Transition(states.get(5), states.get(6), "release")

                    );
            transitionRepository.saveAll(transitions);
            flowRepository.save(new Flow(null, states, transitions));
        };
    }
}
