package Main.model;

import java.security.Guard;

public interface Weapon {


    // EFFECTS: Swings the weapon
    boolean attack(GuardConnection guardConnection);
}
