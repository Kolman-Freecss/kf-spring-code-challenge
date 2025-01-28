package org.kolmanfreecss.test1java;

// ====== INITIAL CODE (WITH ISSUES) ======
public class Weapon {
    private String state = "IDLE";
    private int durability = 100;
    
    public void handleEvent(String event) {
        if (state.equals("IDLE")) {
            if (event.equals("UPGRADE_START")) {
                state = "UPGRADING";
                durability -= 10;
                System.out.println("Started upgrading");
            }
        } else if (state.equals("UPGRADING")) {
            if (event.equals("UPGRADE_SUCCESS")) {
                state = "ENHANCED";
                durability += 20;
            } else if (event.equals("UPGRADE_FAIL")) {
                state = "BROKEN";
                durability = 0;
            }
        }
        // More nested conditionals...
    }
    
    public void printStatus() {
        System.out.println("State: " + state + " | Durability: " + durability);
    }
}