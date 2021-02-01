package test.model;

import Main.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwordTest {

    private Sword testSword;
    private GuardConnection testCorrectGuard;
    private GuardConnection testWrongGuard;
    private Room testCorrectRoom;
    private Room testWrongRoom;

    @BeforeEach
    public void runBefore() {
        testCorrectRoom = new Room();
        testWrongRoom = new Room();
        testCorrectGuard = new GuardConnection(testCorrectRoom, "north");
        testWrongGuard = new GuardConnection(testWrongRoom, "south");
        testSword = new Sword(testCorrectGuard, "testSword");
    }

    @Test
    public void testAttack() {
        assertFalse(testSword.attack(testWrongGuard));
        assertTrue(testSword.attack(testCorrectGuard));
    }

    @Test
    public void testGetType() {
        assertEquals("Sword",testSword.getType());
    }

}
