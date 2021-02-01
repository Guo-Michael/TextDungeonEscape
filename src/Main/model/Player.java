package Main.model;

import Main.observer.AnnoyingObserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Player extends Observable {

    public List<String> commands;
    public List<Item> inventory;
    public Room currentRoom;
    private String name;
    public Map<Room,Integer> roomsVisited = new HashMap<>();
    private AnnoyingObserver annoyingObserver;

    public Player() {
        commands = new ArrayList<>();
        commands.add("go north");
        commands.add("go west");
        commands.add("go east");
        commands.add("go south");
        inventory = new ArrayList<>();
        currentRoom = new Room();
        name = "Player";
        annoyingObserver = new AnnoyingObserver();
        addObserver(annoyingObserver);
    }


    // EFFECTS: prints out all possible commands
    public List<String> getCommands() {
        return commands;
    }

    // MODIFIES: this
    // EFFECTS: adds a command if not already there
    public void addCommand(String c) {
        if (!commands.contains(c)) {
            commands.add(c);
        }
    }

    // REQUIRES: commands are in the list
    // MODIFIES: this
    // EFFECTS: removes the command from the list
    public void removeCommand(String c) {
        if (commands.contains(c)) {
            commands.remove(c);
        }
    }

    // EFFECTS: returns the room the player is currently in
    public Room getRoom() {
        return currentRoom;
    }

    // MODIFIES: this
    // EFFECTS: Moves the player into the inputted room
    public void moveRoom(Room r) {

        if (!(currentRoom == r)) {
            currentRoom.removeExitingPlayer(this);
            currentRoom = r;
            setChanged();
            notifyObservers(r);
            int roomsNowVisited = roomsVisited.keySet().size();
            roomsVisited.put(currentRoom,++roomsNowVisited);
            for (Item i : r.items) {
                String newCommand = "pick up " + i.getName();
                commands.add(newCommand);
            }
            r.addEnteringPlayer(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds item to the player
    public void addItem(Item i) {
        inventory.add(i);
    }

    // MODIFIES: this
    // EFFECTS: Removes item from the player
    public void removeItem(Item i) {
        inventory.remove(i);
    }

    // MODIFIES: this, Room
    // EFFECTS: Removes all items from room r and adds it to the player
    public void pickup(Room r) {
        for (Item i : r.items) {
            pickup(i,r);
        }
    }

    // REQUIRES: Item is in room r
    // MODIFIES: this, Room
    // EFFECTS: Removes input item from room r and adds it to the player
    public void pickup(Item i, Room r) {
        addItem(i);
        r.removeItem(i);
        String itemName = i.getName();
        String newUseCommand = "use " + itemName;
        String newDropCommand = "drop " + itemName;
        addCommand(newUseCommand);
        addCommand(newDropCommand);
        removeCommand("pick up " + itemName);
    }

    // REQUIRES: Item is in player's inventory
    // MODIFIES: this, Room
    // EFFECTS: Removes item from player and places it in Room r
    public void dropItem(Item i, Room r) {
        removeItem(i);
        r.addItem(i);
        removeCommand("use " + i.getName());
        removeCommand("drop " + i.getName());
    }

    // EFFECTS: Returns the number of times the Observer detected a room switch
    public int getNumRoomsSwitched() {
        return annoyingObserver.getNumRoomsSwitched();
    }

    // EFFECTS: Overrides original equals method; returns true when the two players's commands and name match
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return commands.equals(player.commands)
                && name.equals(player.name);
    }

    // EFFECTS: Overrides original hashcode method to only consider the commands and name of players
    @Override
    public int hashCode() {
        return Objects.hash(commands, name);
    }
}
