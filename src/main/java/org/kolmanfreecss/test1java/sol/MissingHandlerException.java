package org.kolmanfreecss.test1java.sol;

public class MissingHandlerException extends RuntimeException {
    public MissingHandlerException(WeaponState state) {
        super("No handler registered for state: " + state);
    }
}