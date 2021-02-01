package Main.model;

public class GuardConnection extends Connection {

//    public static final int MAX_HP = 3;
//    public int hp = MAX_HP;


    // Constructs a Connection for a Room
    public GuardConnection(Room r, String dir) {
        toRoom = r;
        direction = dir;
        isLocked = true;
    }

    // EFFECTS: returns the item necessary for unlocking
    @Override
    public String howToUnlock() {
        return "Weapon";
    }

    // MODIFIES: this
    // EFFECTS: unlocks the connection
    @Override
    public void unlock() {
        isLocked = false;
    }
}
