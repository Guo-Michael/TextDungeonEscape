package test.model;

import Main.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuardConnectionTest {
    private GuardConnection conNorth;
    private GuardConnection conEast;
    private GuardConnection conWest;
    private GuardConnection conSouth;
    private Room northRoom = new Room();
    private Room eastRoom = new Room();
    private Room westRoom = new Room();
    private Room southRoom = new Room();

    @BeforeEach
    public void runBefore() {
        conNorth = new GuardConnection(northRoom,"north");
        conEast = new GuardConnection(eastRoom,"east");
        conWest = new GuardConnection(westRoom,"west");
        conSouth = new GuardConnection(southRoom,"south");
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

//        for (int i = 0; i <= conNorth.MAX_HP; i++) {
//            conNorth.unlock();
//        }
//
//        for (int i = 0; i < conNorth.MAX_HP; i++) {
//            conSouth.unlock();
//        }
        conNorth.unlock();
        conSouth.unlock();

        assertFalse(conNorth.getLockStat());
        assertTrue(conSouth.getLockStat());

    }

    @Test
    public void testHowToUnlock() {
        assertEquals("Weapon",conNorth.howToUnlock());
    }


}
