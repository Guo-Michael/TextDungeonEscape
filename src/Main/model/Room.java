package Main.model;

import java.util.*;

public class Room {

    public List<Item> items;
    public List<Connection> connections;
    public List<Player> players;


    // Constructs a room
    // EFFECTS: Room has no items
    public Room() {
        items = new ArrayList<>();
        connections = new ArrayList<>();
        players = new ArrayList<>();
    }


    // REQUIRES: the input room isn't already in the connections list
    // MODIFIES: this
    // EFFECTS: Connects this room to the input room in a direction
    public void connectRoomOpenly(Room r, String dir) {
        Connection con = new OpenConnection(r, dir);
        connections.add(con);
    }

    // REQUIRES: the input room isn't already in the connections list
    // MODIFIES: this
    // EFFECTS: Connects this room to the input room in a direction
    public void connectLockedRoom(Room r, String dir) {
        Door d = new Door(r,dir);
        connections.add(d);
    }

    // REQUIRES: the input room isn't already in the connections list
    // MODIFIES: this
    // EFFECTS: Connects this room to the input room in a direction
    public void connectGuardedRoom(Room r, String dir) {
        GuardConnection guardedCon = new GuardConnection(r,dir);
        connections.add(guardedCon);
    }


    // EFFECTS: returns true if the input direction leads to another room
    public boolean existsRoomAhead(String dir) {
        for (Connection c : connections) {
            if (dir.equals(c.getDirection())) {
                return true;
            }
        }
        return false;

    }

    // REQUIRES: the input direction connects to a valid room
    // EFFECTS: returns the Room in the input direction
    public Room roomAhead(String dir) throws NoValidRoomsException {
        for (Connection c : connections) {
            if (dir.equals(c.getDirection())) {
                return c.getRoom();
            }
        }
        throw new NoValidRoomsException();
    }

    // EFFECTS: returns true if the Room in the input direction is locked
    public boolean isRoomAheadLocked(String dir) {
        for (Connection c: connections) {
            if (dir.equals(c.getDirection())) {
                return c.isLocked;
            }
        }
        return false;
    }

    // REQUIRES: the item is in the room
    // MODIFIES: this
    // EFFECTS: Remove the item from the room
    public void removeItem(Item i) {
        if (items.contains(i)) {
            items.remove(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: Add the item in the room
    public void addItem(Item i) {
        items.add(i);
    }

    // MODIFIES: this
    // EFFECTS: Adds the incoming player into the Room
    public void addEnteringPlayer(Player p) {
        if (!players.contains(p)) {
            players.add(p);
            p.moveRoom(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes the exiting player out of the Room
    public void removeExitingPlayer(Player p) {
        players.remove(p);
    }

    // EFFECTS: Overrides original equals method; returns true when the two rooms' items, connections, and players match
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return items.equals(room.items)
                && connections.equals(room.connections)
                && players.equals(room.players);
    }

    // EFFECTS: Overrides original hashcode method to only consider the items, connections, and players of rooms
    @Override
    public int hashCode() {
        return Objects.hash(items, connections, players);
    }
}
