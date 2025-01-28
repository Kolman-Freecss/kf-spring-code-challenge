package org.kolmanfreecss.test1java.sol;

// ====== STATE HANDLER INTERFACE ======
public interface IStateHandler {
    TransitionResult handleEvent(WeaponEvent event);
    WeaponState supportedState();
}