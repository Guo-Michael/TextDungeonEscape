package Main.ui;

import Main.model.*;
import java.util.ArrayList;

public class GameMap {


    public static Room startRoom;
    public static Room dungeonRoom1;
    public static Room dungeonRoom2;
    public static Room dungeonRoom3;
    public static Room dungeonRoom4;
    public static Room dungeonRoomWithKey1;
    public static Room dungeonRoomExit;

    public static Room forestEntry;
    public static Room forestRoom1;
    public static Room forestRoom2;
    public static Room forestRoom3;
    public static Room forestRoom4;
    public static Room forestRoom5;
    public static Room forestRoomToCabin;

    public static Room cabinRoom1;

    public static Room forestRoom6;
    public static Room forestRoom7;
    public static Room forestRoom8;
    public static Room forestRoom9;
    public static Room forestRoomA;
    public static Room forestRoomWithKey2;
    public static Room forestRoomExit;

    public static Door dungeonDoor;
    public static Door cabinDoor;
    public static GuardConnection finalConnection;
    public static Key dungeonKey;
    public static Key forestKey;
    public static Sword forestSword;
    public static Room finalRoom;

    public GameMap() {
        startRoom = new Room();
        finalRoom = new Room();
        initializeDungeonRooms();
        initializeForestRooms();
        initializeDungeonConnections();
        initializeForestConnections1();
        initializeForestConnections2();
        initializeItems();
    }

    private void initializeItems() {
        dungeonKey = new Key(dungeonDoor,"Dungeon Key");
        dungeonRoomWithKey1.items.add(dungeonKey);
        forestKey = new Key(cabinDoor,"Forest Key");
        forestRoomWithKey2.items.add(forestKey);
        forestSword = new Sword(finalConnection,"Sword");
        cabinRoom1.items.add(forestSword);
    }

    private void initializeForestConnections2() {
        cabinRoom1.connectRoomOpenly(forestRoomToCabin,"west");
        forestRoom6.connectRoomOpenly(forestRoom5,"south");
        forestRoom6.connectRoomOpenly(forestRoom7,"north");
        forestRoom7.connectRoomOpenly(forestRoom6,"south");
        forestRoom7.connectRoomOpenly(forestRoom8,"east");
        forestRoom7.connectRoomOpenly(forestRoomExit,"north");
        forestRoom7.connectRoomOpenly(forestRoom9,"west");
        forestRoom8.connectRoomOpenly(forestRoom7,"west");
        forestRoom9.connectRoomOpenly(forestRoom7,"east");
        forestRoom9.connectRoomOpenly(forestRoomA,"west");
        forestRoomA.connectRoomOpenly(forestRoom9,"east");
        forestRoomA.connectRoomOpenly(forestRoomWithKey2,"south");
        forestRoomWithKey2.connectRoomOpenly(forestRoomA,"north");
        forestRoomExit.connectRoomOpenly(forestRoom7,"south");
        finalConnection = new GuardConnection(finalRoom,"north");
        forestRoomExit.connections.add(finalConnection);
        finalRoom.connectRoomOpenly(forestRoomExit,"south");
    }

    private void initializeForestConnections1() {
        forestEntry.connectRoomOpenly(dungeonRoomExit,"south");
        forestEntry.connectRoomOpenly(forestRoom1,"north");
        forestRoom1.connectRoomOpenly(forestEntry,"south");
        forestRoom1.connectRoomOpenly(forestRoom2,"west");
        forestRoom1.connectRoomOpenly(forestRoom5,"north");
        forestRoom1.connectRoomOpenly(forestRoom4,"east");
        forestRoom2.connectRoomOpenly(forestRoom1,"east");
        forestRoom2.connectRoomOpenly(forestRoom3,"south");
        forestRoom3.connectRoomOpenly(forestRoom2,"north");
        forestRoom4.connectRoomOpenly(forestRoom1,"west");
        forestRoom4.connectRoomOpenly(forestRoomToCabin,"north");
        forestRoom5.connectRoomOpenly(forestRoom1,"south");
        forestRoom5.connectRoomOpenly(forestRoomToCabin,"east");
        forestRoom5.connectRoomOpenly(forestRoom6,"north");
        forestRoomToCabin.connectRoomOpenly(forestRoom4,"south");
        forestRoomToCabin.connectRoomOpenly(forestRoom5,"west");
        cabinDoor = new Door(cabinRoom1,"east");
        forestRoomToCabin.connections.add(cabinDoor);
    }

    private void initializeDungeonConnections() {
        startRoom.connectRoomOpenly(dungeonRoom1,"south");
        dungeonRoom1.connectRoomOpenly(dungeonRoomWithKey1,"east");
        dungeonRoom1.connectRoomOpenly(startRoom,"north");
        dungeonRoom1.connectRoomOpenly(dungeonRoom3,"west");
        dungeonRoom1.connectRoomOpenly(dungeonRoom2,"south");
        dungeonRoomWithKey1.connectRoomOpenly(dungeonRoom1,"west");
        dungeonRoom2.connectRoomOpenly(dungeonRoom1,"north");
        dungeonRoom3.connectRoomOpenly(dungeonRoom1,"east");
        dungeonRoom3.connectRoomOpenly(dungeonRoom4,"west");
        dungeonRoom4.connectRoomOpenly(dungeonRoom3,"east");
        dungeonRoom4.connectRoomOpenly(dungeonRoomExit,"north");
        dungeonRoomExit.connectRoomOpenly(dungeonRoom4,"south");
        dungeonDoor = new Door(forestEntry,"north");
        dungeonRoomExit.connections.add(dungeonDoor);
    }

    private void initializeDungeonRooms() {
        dungeonRoom1 = new Room();
        dungeonRoom2 = new Room();
        dungeonRoom3 = new Room();
        dungeonRoom4 = new Room();
        dungeonRoomWithKey1 = new Room();
        dungeonRoomExit = new Room();

    }

    private void initializeForestRooms() {
        forestEntry = new Room();
        forestRoom1 = new Room();
        forestRoom2 = new Room();
        forestRoom3 = new Room();
        forestRoom4 = new Room();
        forestRoom5 = new Room();
        forestRoom6 = new Room();
        forestRoom7 = new Room();
        forestRoom8 = new Room();
        forestRoom9 = new Room();
        forestRoomA = new Room();
        forestRoomWithKey2 = new Room();
        forestRoomToCabin = new Room();
        forestRoomExit = new Room();
        cabinRoom1 = new Room();
    }

    // EFFECTS: Returns the output strings required for a specific room when a player steps into that room
    public static ArrayList<String> welcomePhrase(Player p) {
        ArrayList<String> storyText = welcomeRoomPhrase(p);
        for (String s : describeItemsPhrase(p)) {
            storyText.add(s);
        }
        return storyText;
    }

    private static ArrayList<String> welcomeRoomPhrase(Player p) {
        ArrayList<String> storyText = new ArrayList<>();
        if (p.currentRoom == startRoom) {
            storyText.add("You find yourself in a dungeon... with no memory of how you got there.");
            storyText.add("You notice that it's dark outside; everyone must be asleep.");
            storyText.add("Fortunately, your room is unlocked.");
            storyText.add("You need to escape before anyone notices. What do you do?");
            return storyText;
        } else {
            if (p.currentRoom == dungeonRoomExit) {
                storyText.add("You find a door with a lock to the north.");
                return storyText;
            } else {
                return welcomeForestOrOtherPhrase(p);
            }
        }
    }

    private static ArrayList<String> welcomeForestOrOtherPhrase(Player p) {
        ArrayList<String> storyText = new ArrayList<>();
        if (p.currentRoom == forestEntry) {
            storyText.add("You stand at the dungeon gateway in a forest.");
            return storyText;
        } else {
            if (p.currentRoom == forestRoomToCabin) {
                storyText.add("You see a cabin to the east.");
                return storyText;
            } else {
                if (p.currentRoom == forestRoomExit) {
                    return welcomeExitForestPhrase(p);
                } else {
                    return welcomeOtherPhrase(p);
                }
            }
        }
    }

    private static ArrayList<String> welcomeExitForestPhrase(Player p) {
        ArrayList<String> storyText = new ArrayList<>();
        if (p.currentRoom.isRoomAheadLocked("north")) {
            storyText.add("You see a guard blocking your way.");
            return storyText;
        } else {
            storyText.add("The guard you have killed lies on the floor.");
            return storyText;
        }
    }

    private static ArrayList<String> welcomeOtherPhrase(Player p) {
        ArrayList<String> storyText = new ArrayList<>();
        if (p.currentRoom == cabinRoom1) {
            storyText.add("You find yourself in a cabin.");
            return storyText;
        } else {
            storyText.add("You continue making your way through.");
            return storyText;
        }
    }

    private static ArrayList<String> describeItemsPhrase(Player p) {
        ArrayList<String> storyText = new ArrayList<>();
        for (Item i : p.currentRoom.items) {
            storyText.add("You find a " + i.getName() + " on the ground.");
        }
        return storyText;
    }

}
