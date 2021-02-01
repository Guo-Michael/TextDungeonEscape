package test.model;

import Main.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player testPlayer;
    private Room testRoom = new Room();

    @BeforeEach
    public void runBefore() {
        testPlayer = new Player();
        testPlayer.currentRoom = testRoom;

    }

    @Test
    public void testAddCommand() {
        testPlayer.addCommand("go north");
        assertEquals(4,testPlayer.commands.size());
        testPlayer.addCommand("new command");
        assertEquals(5,testPlayer.commands.size());
        assertTrue(testPlayer.commands.contains("new command"));
    }

    @Test
    public void testRemoveCommand() {
        testPlayer.removeCommand("");
        assertEquals(4,testPlayer.commands.size());
        testPlayer.removeCommand("go north");
        assertEquals(3,testPlayer.commands.size());
    }

    @Test
    public void testGetRoom() {
        assertEquals(testRoom,testPlayer.getRoom());
    }

    @Test
    public void testMoveToEmptyRoom() {
        Room testRoom2 = new Room();
        testPlayer.moveRoom(testRoom2);
        assertEquals(testRoom2,testPlayer.getRoom());
        assertEquals(4,testPlayer.commands.size());
    }

    @Test
    public void testMoveToRoomNewCommands() {
        Sword testSword = new Sword(new GuardConnection(new Room(), "north"), "Test Sword");
        Key testKey = new Key(new Door(new Room(), "south"), "Test Key");
        Room testRoom2 = new Room();
        testRoom2.items.add(testSword);
        testRoom2.items.add(testKey);
        testPlayer.moveRoom(testRoom2);
        assertEquals(0,testRoom.players.size());
        assertEquals(1,testRoom2.players.size());
        assertEquals(testRoom2, testPlayer.currentRoom);
        assertEquals(6,testPlayer.commands.size());
        assertTrue(testPlayer.commands.contains("pick up Test Sword"));
        assertTrue(testPlayer.commands.contains("pick up Test Key"));
    }

    @Test
    public void testAddItem() {
        Sword testSword = new Sword(new GuardConnection(new Room(), "north"), "Test Sword");
        testPlayer.addItem(testSword);
        assertEquals(1,testPlayer.inventory.size());
        assertTrue(testPlayer.inventory.contains(testSword));
    }

    @Test
    public void testRemoveItem() {
        Sword testSword = new Sword(new GuardConnection(new Room(), "north"), "Test Sword");
        testPlayer.addItem(testSword);
        assertEquals(1,testPlayer.inventory.size());
        assertTrue(testPlayer.inventory.contains(testSword));
        testPlayer.removeItem(testSword);
        assertEquals(0, testPlayer.inventory.size());
        assertFalse(testPlayer.inventory.contains(testSword));
    }

    @Test
    public void testPickupRoom() {
        Sword testSword = new Sword(new GuardConnection(new Room(), "north"), "Test Sword");
        Key testKey = new Key(new Door(new Room(), "south"), "Test Key");
        testRoom.items.add(testSword);
        testRoom.items.add(testKey);
        testPlayer.pickup(testRoom);
        assertEquals(2, testPlayer.inventory.size());
        assertEquals(0,testRoom.items.size());
        assertEquals(8,testPlayer.commands.size());
        assertTrue(testPlayer.inventory.contains(testKey));
        assertTrue(testPlayer.inventory.contains(testSword));
    }

    @Test
    public void testPickup1inRoom() {
        Sword testSword = new Sword(new GuardConnection(new Room(), "north"), "Test Sword");
        Key testKey = new Key(new Door(new Room(), "south"), "Test Key");
        testRoom.items.add(testSword);
        testRoom.items.add(testKey);
        testPlayer.pickup(testKey, testRoom);
        assertEquals(1, testPlayer.inventory.size());
        assertEquals(1,testRoom.items.size());
        assertEquals(6,testPlayer.commands.size());
        assertTrue(testPlayer.inventory.contains(testKey));
        assertFalse(testPlayer.inventory.contains(testSword));

    }

    @Test
    public void testDropItem() {
        Sword testSword = new Sword(new GuardConnection(new Room(), "north"), "Test Sword");
        Key testKey = new Key(new Door(new Room(), "south"), "Test Key");
        testRoom.items.add(testSword);
        testRoom.items.add(testKey);
        testPlayer.pickup(testKey, testRoom);
        testPlayer.pickup(testSword,testRoom);
        testPlayer.dropItem(testKey,testRoom);
        assertEquals(1,testRoom.items.size());
        assertEquals(1,testPlayer.inventory.size());
        assertEquals(6,testPlayer.commands.size());
        assertTrue(testRoom.items.contains(testKey));
        assertTrue(testPlayer.inventory.contains(testSword));
    }
}
