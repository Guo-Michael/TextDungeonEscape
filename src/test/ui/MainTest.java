package test.ui;

import Main.ui.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class MainTest {
//    private Main game;
//
//    @BeforeEach
//    public void runBefore() throws IOException {
//        game = new Main();
//    }

//    @Test
//    public void testNoException() throws IOException {
//        try {
//            Player testPlayer = game.thePlayer;
//            Main.executeCommand("go west", testPlayer);
//            Main.executeCommand("go east", testPlayer);
//            Main.executeCommand("go north", testPlayer);
//            Main.executeCommand("go south", testPlayer);
//            testPlayer.inventory.add(new Sword(new GuardConnection(new Room(), "north"), "TestSword"));
//            testPlayer.addCommand("drop TestSword");
//            Main.executeCommand("drop TestSword", testPlayer);
//        } catch (CannotDoException e) {
//            fail();
//        }
//    }

//    @Test
//    public void testDefaultWithException() throws IOException {
//        try {
//            Main.executeCommand("go up",game.thePlayer);
//        } catch (CannotDoException e) {
//
//        }
//    }

//    @Test
//    public void testAddCommandWithException() throws IOException {
//        try {
//            Player testPlayer = game.thePlayer;
//            testPlayer.inventory.add(new Sword(new GuardConnection(new Room(), "north"), "TestSword"));
//            testPlayer.addCommand("drop TestSword");
//            Main.executeCommand("drop the TestSword", testPlayer);
//        } catch (CannotDoException e){
//
//        }
//    }

    @Test
    public void testExecuteCommand() {
        //!!!
    }

}