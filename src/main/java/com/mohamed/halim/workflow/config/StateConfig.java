package com.mohamed.halim.workflow.config;

import com.mohamed.halim.workflow.model.Event;
import com.mohamed.halim.workflow.model.State;
import com.mohamed.halim.workflow.model.Transition;
import com.mohamed.halim.workflow.repo.StateRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class StateConfig extends StateMachineConfigurerAdapter<State, Event> {
    private final StateRepository stateRepository;
    private List<State> states;

    @PostConstruct
    private void init() {
        states = stateRepository.findAll();
    }

    @Override
    public void configure(StateMachineStateConfigurer<State, Event> statesConfig) throws Exception {
        statesConfig.withStates().states(new HashSet<>(states));
        for(var state : states) {
            if(state.isEnd()) {
                statesConfig.withStates().end(state);
            }
            if(state.isInitial()) {
                statesConfig.withStates().initial(state);
            }
        }
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<State, Event> transitionsConfig) throws Exception {
        for (State state : states) {
            for (Transition transition : state.getTransitions()) {
                transitionsConfig.withExternal().source(transition.getFrom()).target(transition.getTo())
                        .event(transition.getEvent());
            }
        }
    }
}
