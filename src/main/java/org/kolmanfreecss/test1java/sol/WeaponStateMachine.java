package org.kolmanfreecss.test1java.sol;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WeaponStateMachine {
    private WeaponState currentState;
    private final AtomicInteger durability;
    private final Map<WeaponState, IStateHandler> handlers;

    WeaponStateMachine(WeaponState initialState,
                      int initialDurability,
                      Map<WeaponState, IStateHandler> handlers) {
        this.currentState = initialState;
        this.durability = new AtomicInteger(initialDurability);
        this.handlers = handlers;
    }

    public synchronized void processEvent(WeaponEvent event) {
        IStateHandler handler = handlers.get(currentState);
        if (handler == null) {
            throw new MissingHandlerException(currentState);
        }
        
        TransitionResult result = handler.handleEvent(event);
        durability.addAndGet(result.durabilityChange());
        currentState = result.newState();
    }
    
    public int getDurability() {
        return durability.get();
    }
    
    public WeaponState getCurrentState() {
        return currentState;
    }
}