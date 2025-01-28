package org.kolmanfreecss.test1java.sol;

public class IllegalEventException extends RuntimeException {
    public IllegalEventException(WeaponEvent event) {
        super("Invalid event " + event + " for current state");
    }
    
    public IllegalEventException(String message) {
        super(message);
    }
}