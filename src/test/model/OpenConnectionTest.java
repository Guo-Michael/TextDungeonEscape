package test.model;

import Main.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class OpenConnectionTest {
    private OpenConnection conNorth;
    private OpenConnection conEast;
    private OpenConnection conWest;
    private OpenConnection conSouth;
    private Room northRoom = new Room();
    private Room eastRoom = new Room();
    private Room westRoom = new Room();
    private Room southRoom = new Room();

    @BeforeEach
    public void runBefore() {
        conNorth = new OpenConnection(northRoom,"north");
        conEast = new OpenConnection(eastRoom,"east");
        conWest = new OpenConnection(westRoom,"west");
        conSouth = new OpenConnection(southRoom,"south");
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
        assertFalse(conNorth.getLockStat());
        assertFalse(conSouth.getLockStat());
        assertFalse(conEast.getLockStat());
        assertFalse(conWest.getLockStat());
    }

    @Test
    public void unlock() {
        conNorth.unlock();
        assertFalse(conNorth.getLockStat());
    }

    @Test
    public void testHowToUnlock() {
        assertEquals("Nothing!",conNorth.howToUnlock());
    }


}
