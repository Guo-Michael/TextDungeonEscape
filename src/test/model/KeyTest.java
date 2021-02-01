package test.model;

import Main.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeyTest {

    private Key testKey;
    private Door testCorrectDoor;
    private Door testWrongDoor;
    private Room testCorrectRoom;
    private Room testWrongRoom;

    @BeforeEach
    public void runBefore() {
        testCorrectRoom = new Room();
        testWrongRoom = new Room();
        testCorrectDoor = new Door(testCorrectRoom, "north");
        testWrongDoor = new Door(testWrongRoom, "south");
        testKey = new Key(testCorrectDoor, "testKey");
    }

    @Test
    public void testTryUnlock() {
        assertFalse(testKey.tryUnlock(testWrongDoor));
        assertTrue(testWrongDoor.getLockStat());
        assertTrue(testKey.tryUnlock(testCorrectDoor));
        assertFalse(testCorrectDoor.getLockStat());

    }
}
