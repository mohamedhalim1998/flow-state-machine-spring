package com.mohamed.halim.workflow.config;

import com.mohamed.halim.workflow.model.Event;
import com.mohamed.halim.workflow.model.State;
import com.mohamed.halim.workflow.model.Transition;
import com.mohamed.halim.workflow.repo.StateRepository;
import com.mohamed.halim.workflow.repo.TransitionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;

import java.util.HashSet;
import java.util.List;

@Configuration
@Slf4j
public class FlowStateConfig {
    @Bean
    @Scope("prototype")
    @Lazy
    public StateMachine<State, Event> flowMachine(StateRepository stateRepository, TransitionRepository transitionRepository) throws Exception {
        var builder = StateMachineBuilder.<State, Event>builder();
        var statesConfig = builder.configureStates();
        List<State> states = stateRepository.findAll();
        statesConfig.withStates().states(new HashSet<>(states));
        for (var state : states) {
            if (state.isEnd()) {
                statesConfig.withStates().end(state);
            }
            if (state.isInitial()) {
                statesConfig.withStates().initial(state);
            }
        }
        var transitionsConfig = builder.configureTransitions();
        var transitions = transitionRepository.findAll();
        for (Transition transition : transitions) {
            transitionsConfig.withExternal().source(transition.getFrom()).target(transition.getFrom())
                    .event(transition.getEvent());
        }

        return builder.build();
    }
}
