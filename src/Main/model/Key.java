package Main.model;

public class Key implements Item {

    public Door toUnlock;
    public String name;

    public Key(Door d, String n) {
        toUnlock = d;
        name = n;
    }


    // MODIFIES: tryDoor
    // EFFECTS: if toUnlock matches tryDoor, sets the Door to unlocked state; otherwise, nothing
    public boolean tryUnlock(Door tryDoor) {
        if (toUnlock == tryDoor) {
            tryDoor.unlock();
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "Key";
    }

    @Override
    public String getName() {
        return name;
    }
}
