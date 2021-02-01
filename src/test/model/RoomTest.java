package test.model;

import Main.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private Room testRoom;
    private OpenConnection testOpenCon;
    private GuardConnection testGuardCon;
    private Door testDoor;
    private Room testRoom2;

    @BeforeEach
    public void runBefore() {
        testRoom = new Room();
        testRoom2 = new Room();
        testOpenCon = new OpenConnection(testRoom2, "north");
        testGuardCon = new GuardConnection(testRoom2,"south");
        testDoor = new Door(testRoom2, "west");
        testRoom.connections.add(testOpenCon);
    }

    @Test
    public void testExistsRoomAhead() {
        assertFalse(testRoom.existsRoomAhead(""));
        assertFalse(testRoom.existsRoomAhead("east"));
        assertFalse(testRoom.existsRoomAhead("noth"));
        assertTrue(testRoom.existsRoomAhead("north"));
    }

    @Test
    public void testRoomAheadWithException() {
        try {
            assertEquals(testRoom2, testRoom.roomAhead("north"));
        } catch (NoValidRoomsException e){
            fail();
        }

        try {
            testRoom.roomAhead("west");
        } catch (NoValidRoomsException e) {

        }
    }

    @Test
    public void testConnectRoomOpenly() {
        Room newTestRoom = new Room();
        testRoom.connectRoomOpenly(newTestRoom, "east");
        assertEquals(2, testRoom.connections.size());
        try {
            assertEquals(newTestRoom, testRoom.roomAhead("east"));
        } catch (NoValidRoomsException e) {
            fail();
        }
    }

    @Test
    public void testConnectLockedRoom() {
        Room newTestRoom = new Room();
        testRoom.connectLockedRoom(newTestRoom, "east");
        assertEquals(2, testRoom.connections.size());

        try {
            assertEquals(newTestRoom,testRoom.roomAhead("east"));
        } catch (NoValidRoomsException e){
            fail();
        }
    }

    @Test
    public void testConnectGuardedRoom() {
        Room newTestRoom = new Room();
        testRoom.connectGuardedRoom(newTestRoom, "east");
        assertEquals(2, testRoom.connections.size());

        try {
            assertEquals(newTestRoom, testRoom.roomAhead("east"));
        } catch (NoValidRoomsException e) {
            fail();
        }

    }

    @Test
    public void testIsRoomAheadLocked() {
        Room newTestRoom = new Room();
        testRoom.connectLockedRoom(newTestRoom, "east");
        assertFalse(testRoom.isRoomAheadLocked("west"));
        assertTrue(testRoom.isRoomAheadLocked("east"));
        assertFalse(testRoom.isRoomAheadLocked("north"));
    }

    @Test
    public void testAddItem() {
        assertEquals(0, testRoom.items.size());
        testRoom.items.add(new Key(testDoor,"testKey"));
        assertEquals(1,testRoom.items.size());
    }

    @Test
    public void testAddEnteringPlayer() {
        Player testPlayer = new Player();
        assertEquals(0, testRoom.players.size());
        testRoom.addEnteringPlayer(testPlayer);
        assertEquals(1,testRoom.players.size());
        assertEquals(testRoom,testPlayer.currentRoom);
    }

    @Test
    public void testRemoveExitingPlayer() {
        Player testPlayer = new Player();
        testRoom.addEnteringPlayer(testPlayer);
        testRoom.removeExitingPlayer(testPlayer);
        assertEquals(0,testRoom.players.size());
    }

}
