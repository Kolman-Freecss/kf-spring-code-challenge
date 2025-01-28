// ====== ABSTRACT BASE HANDLER ======
package org.kolmanfreecss.test1java.sol;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractStateHandler implements IStateHandler {
    protected final AtomicInteger durability;
    
    protected AbstractStateHandler(int initialDurability) {
        this.durability = new AtomicInteger(initialDurability);
    }
    
    protected void validateDurability(int minValue) {
        if (durability.get() < minValue) {
            throw new IllegalStateException("Durability below " + minValue);
        }
    }
    
    protected void validateEvent(WeaponEvent event, WeaponEvent... allowed) {
        for (WeaponEvent e : allowed) {
            if (e == event) return;
        }
        throw new IllegalEventException(event);
    }
}