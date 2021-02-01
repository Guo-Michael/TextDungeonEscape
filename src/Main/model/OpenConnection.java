package Main.model;


public class OpenConnection extends Connection {

    // Constructs a Connection for a Room
    public OpenConnection(Room r, String dir) {
        toRoom = r;
        direction = dir;
        isLocked = false;
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
        return "Nothing!";
    }
}
