package test.model;

import Main.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoorTest {
    private Door conNorth;
    private Door conEast;
    private Door conWest;
    private Door conSouth;
    private Room northRoom = new Room();
    private Room eastRoom = new Room();
    private Room westRoom = new Room();
    private Room southRoom = new Room();

    @BeforeEach
    public void runBefore() {
        conNorth = new Door(northRoom,"north");
        conEast = new Door(eastRoom,"east");
        conWest = new Door(westRoom,"west");
        conSouth = new Door(southRoom,"south");
    }

    @Test
    public void testGetDirection() {
        assertEquals("north",conNorth.getDirection());
        assertEquals("west",conWest.getDirection());
        assertEquals("east",conEast.getDirection());
        assertEquals("south",conSouth.getDirection());
    }

    @Test
    public void testGetRoom() {
        assertEquals(northRoom,conNorth.getRoom());
        assertEquals(westRoom,conWest.getRoom());
        assertEquals(eastRoom,conEast.getRoom());
        assertEquals(southRoom,conSouth.getRoom());
    }

    @Test
    public void testGetLockStat() {
        assertTrue(conNorth.getLockStat());
        assertTrue(conSouth.getLockStat());
        assertTrue(conEast.getLockStat());
        assertTrue(conWest.getLockStat());
    }

    @Test
    public void unlock() {

        conNorth.unlock();
        conSouth.unlock();

        assertFalse(conNorth.getLockStat());
        assertFalse(conSouth.getLockStat());

    }

    @Test
    public void testHowToUnlock() {
        assertEquals("Key",conNorth.howToUnlock());
    }


}
