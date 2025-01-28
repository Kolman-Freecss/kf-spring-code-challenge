// ====== BUILDER ======
package org.kolmanfreecss.test1java.sol;

import java.util.HashMap;
import java.util.Map;

public class WeaponBuilder {
    private WeaponState initialState = WeaponState.IDLE;
    private int initialDurability = 100;
    private final Map<WeaponState, IStateHandler> handlers = new HashMap<>();
    
    public WeaponBuilder withInitialState(WeaponState state) {
        this.initialState = state;
        return this;
    }
    
    public WeaponBuilder withInitialDurability(int durability) {
        this.initialDurability = durability;
        return this;
    }
    
    public WeaponBuilder withHandler(IStateHandler handler) {
        handlers.put(handler.supportedState(), handler);
        return this;
    }
    
    public WeaponStateMachine build() {
        validateHandlers();
        return new WeaponStateMachine(
            initialState,
            initialDurability,
            new HashMap<>(handlers)
        );
    }
    
    private void validateHandlers() {
        if (!handlers.containsKey(WeaponState.IDLE)) {
            throw new IllegalStateException("Missing IDLE handler");
        }
    }
}