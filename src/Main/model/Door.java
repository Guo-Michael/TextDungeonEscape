package Main.model;

public class Door extends Connection {

    // Constructs a Connection for a Room
    public Door(Room r, String dir) {
        toRoom = r;
        direction = dir;
        isLocked = true;
    }

    // MODIFIES: this
    // EFFECTS: unlocks the connection
    @Override
    public void unlock() {
        isLocked = false;
    }

    // EFFECTS: returns the item necessary for unlocking
    @Override
    public String howToUnlock() {
        return "Key";
    }
}
