package Main.model;

public abstract class Connection {

    protected Room toRoom;
    protected String direction;
    protected boolean isLocked;


    // EFFECTS: returns the connection's direction
    public String getDirection() {
        return direction;
    }

    // EFFECTS: returns the connection's room
    public Room getRoom() {
        return toRoom;
    }

    // EFFECTS: returns the conection's locked status
    public boolean getLockStat() {
        return isLocked;
    }

    // MODIFIES: this
    // EFFECTS: unlocks the connection
    public abstract void unlock();

    // MODIFIES: this
    // EFFECTS: locks the connection
    public void lock() {
        isLocked = true;
    }

    // EFFECTS: returns the item necessary for unlocking
    public abstract String howToUnlock();

}