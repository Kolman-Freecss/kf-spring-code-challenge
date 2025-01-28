// ====== CONCRETE HANDLERS ======
package org.kolmanfreecss.test1java.sol;

public class IdleStateHandler extends AbstractStateHandler {
    public IdleStateHandler(int durability) {
        super(durability);
    }

    @Override
    public TransitionResult handleEvent(WeaponEvent event) {
        validateEvent(event, WeaponEvent.UPGRADE_START);
        validateDurability(10);
        
        durability.addAndGet(-10);
        return new TransitionResult(WeaponState.UPGRADING, -10);
    }

    @Override
    public WeaponState supportedState() {
        return WeaponState.IDLE;
    }
}