package Main.ui;

import Main.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame  implements ActionListener {

    public static GameMap gameMap;
    public static Player thePlayer;
    public static Background backgroundPane;
    public JTextField inputField;
    private Font inputFont = new Font("Courier New", Font.PLAIN, 36);
    private Font buttonFont = new Font("Segoe Script", Font.BOLD, 14);

    public Main() throws IOException {
        super("Text-Based Adventure Game");

        gameMap = new GameMap();
        thePlayer = new Player();
        thePlayer.currentRoom = gameMap.startRoom;

        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backgroundPane = new Background();
        setContentPane(backgroundPane);

        JButton enterButton = new JButton("Enter");
        enterButton.setActionCommand("enter");
        enterButton.setFont(buttonFont);
        enterButton.setBackground(new Color(86,86,86));
        enterButton.setForeground(new Color(255,255,255));
        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enterButton.addActionListener(this);
        add(enterButton);
        inputField = new JTextField();
        inputField.setMinimumSize(new Dimension(500, 75));
        inputField.setMaximumSize(new Dimension(500, 75));
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setFont(inputFont);
        inputField.setText("");
        inputField.setBackground(new Color(86, 86, 86));
        inputField.setForeground(new Color(255,255,255));
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                goUpdate();
            }
        });
        backgroundPane.input.add(Box.createVerticalStrut(25));
        backgroundPane.input.add(inputField);
        backgroundPane.input.add(Box.createVerticalStrut(15));

        pack();
        setVisible(true);

        ArrayList<String> startText = gameMap.welcomePhrase(thePlayer);
        startText.add("(Type \"command list\" to get a list of possible actions.)");
        updateDraw(startText);

    }

    private void updateDraw(ArrayList<String> storyText) {
        if (Main.thePlayer.currentRoom == Main.gameMap.forestEntry) {
            backgroundPane.setForestBackground();
        }
        if (Main.thePlayer.currentRoom == Main.gameMap.dungeonRoomExit) {
            backgroundPane.setDungeonBackground();
        }
        inputField.setText("");
        backgroundPane.output.newLabels(storyText);
        validate();
        repaint();
    }

    // EFFECTS: Updates all instances and the feedback text panel when the enter key is pressed; otherwise, do nothing
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enter")) {
            goUpdate();
        }
    }

    private void updateGame(String command) {
        ArrayList<String> storyText = new ArrayList<>();
        try {
            storyText.addAll(executeCommand(command, thePlayer));
            storyText.addAll(gameMap.welcomePhrase(thePlayer));
            storyText.add("(Type \"command list\" to get a list of possible actions.)");
            if (thePlayer.currentRoom == gameMap.finalRoom) {
                storyText.clear();
                storyText.add("You made it out!");
                storyText.add("You have switched rooms " + thePlayer.getNumRoomsSwitched() + " times.");
            }
            updateDraw(storyText);
        } catch (CannotDoException exc) {
            solveCannotDoException();
        } catch (IOException exc) {
            storyText.clear();
            storyText.add("You cannot do that!");
            updateDraw(storyText);
        }
    }

    private void goUpdate() {
        String command = inputField.getText();
        updateGame(command);
    }


    private static ArrayList<String> executeCommand(String command, Player p) throws IOException, CannotDoException {
        if (command.equals("command list")) {
            return printCommands(p);
        } else {
            if (command.length() > 3 && command.startsWith("go ")) {
                return directionCommand(command, p);
            } else {
                if (command.length() >= 4) {
                    return actionCommand(command, p);
                } else {
                    throw new CannotDoException();
                }
            }
        }
    }

    private static ArrayList<String> printCommands(Player player) {
        ArrayList<String> storyText = new ArrayList<>();
        storyText.add("You can: (Type one)");
        for (String s : player.getCommands()) {
            storyText.add(s);
        }
        storyText.add("");
        return storyText;
    }


    private static ArrayList<String> directionCommand(String command, Player p) {
        String dir = command.substring(3);
        if (p.currentRoom.existsRoomAhead(dir)) {
            if (!p.currentRoom.isRoomAheadLocked(dir)) {
                try {
                    Room toMove = p.currentRoom.roomAhead(dir);
                    p.moveRoom(toMove);
                    return processOneString("You went " + dir);
                } catch (NoValidRoomsException e) {
                    return processOneString("You cannot go that way!");
                }
            } else {
                return processOneString("The room is locked.");
            }
        } else {
            return processOneString("You cannot go that way!");
        }
    }

    private static ArrayList<String> actionCommand(String command, Player p) throws CannotDoException {
        String action = command.substring(0,4);
        if (action.equals("pick")) {
            return pickupCommand(command, p);
        } else {
            if (action.equals("drop")) {
                return dropCommand(command, p);
            } else {
                if (action.equals("use ")) {
                    return useCommand(command, p);
                } else {
                    throw new CannotDoException();
                }
            }
        }

    }

    private static ArrayList<String> pickupCommand(String command, Player p) throws CannotDoException {
        if (command.length() >= 8) {
            for (Item i : p.currentRoom.items) {
                if (i.getName().equals(command.substring(8))) {
                    p.pickup(i, p.currentRoom);
                    return processOneString("You picked up the " + i.getName() + ".");
                }
            }
        }
        throw new CannotDoException();
    }

    private static ArrayList<String> dropCommand(String command, Player p) throws CannotDoException {
        if (command.length() >= 5) {
            for (Item i : p.inventory) {
                if (i.getName().equals(command.substring(5))) {
                    p.dropItem(i, p.currentRoom);
                    return processOneString("You dropped the " + i.getName() + ".");
                }
            }
        }
        throw new CannotDoException();
    }

    private static ArrayList<String> useCommand(String command, Player p) throws CannotDoException {
        for (Item i : p.inventory) {
            if (i.getName().equals(command.substring(4))) {
                if (i.getType().equals("Key")) {
                    return useKeyCommand((Key) i, command, p);
                } else {
                    if (i.getType().equals("Sword")) {
                        return useSwordCommand((Sword) i, command, p);
                    }
                }
            }
        }
        throw new CannotDoException();
    }

    private static ArrayList<String> useKeyCommand(Key toUse, String command, Player p) {
        for (Connection d : p.currentRoom.connections) {
            if (toUse.toUnlock == d) {
                d.unlock();
                return processOneString("You hear a door unlock.");
            }
        }
        return processOneString("This key isn't unlocking anything here.");
    }

    private static ArrayList<String> useSwordCommand(Sword toUse, String command, Player p) {
        for (Connection g : p.currentRoom.connections) {
            if (toUse.toKill == g) {
                g.unlock();
                return processOneString("You kill the guard in the room.");
            }
        }
        return processOneString("There is no one to kill.");
    }

    private static ArrayList<String> processOneString(String s) {
        ArrayList<String> oneStringInList = new ArrayList<>();
        oneStringInList.add(s);
        return oneStringInList;
    }

    private void solveCannotDoException() {
        ArrayList<String> storyText = new ArrayList<>();
        storyText.add("You cannot do that!");
        storyText.add("(Type \"command list\" to get a list of possible actions.)");
        updateDraw(storyText);
    }

    // EFFECTS: Starts the game
    public static void main(String[] args) throws IOException {
        new Main();

    }

}