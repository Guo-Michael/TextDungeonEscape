package Main.model;

import java.util.List;

public class Sword implements Item, Weapon {

    private String name;
    public GuardConnection toKill;

    public Sword(GuardConnection g, String n) {
        toKill = g;
        name = n;
    }

    // MODIFIES: toKill
    // REQUIRES: Returns true and unlocks toKill when the input g matches; otherwise returns false
    public boolean attack(GuardConnection g) {
        if (g == toKill) {
            toKill.unlock();
            return true;
        }
        return false;
    }

    public String getType() {
        return "Sword";
    }

    public String getName() {
        return name;
    }
}
