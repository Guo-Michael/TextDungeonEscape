package Main.ui;

import javax.swing.*;
import java.awt.*;

public class Background extends JLabel {

    private ImageIcon dungeonBackground = new ImageIcon(System.getProperty("user.dir")
            + System.getProperty("file.separator") + "images" + System.getProperty("file.separator") + "Dungeon.png");
    private ImageIcon forestBackground = new ImageIcon(System.getProperty("user.dir")
            + System.getProperty("file.separator") + "images" + System.getProperty("file.separator") + "Forest.png");
    public FeedbackPanel output;
    public InputTextPanel input;

    public Background() {
        setIcon(dungeonBackground);
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxlayout);

        output = new FeedbackPanel();
        output.setAlignmentX(Component.CENTER_ALIGNMENT);
        input = new InputTextPanel();
        input.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(50));
        add(output);
        add(Box.createVerticalStrut(25));
        add(input);

    }

    public void setForestBackground() {
        setIcon(forestBackground);
    }

    public void setDungeonBackground() {
        setIcon(dungeonBackground);
    }

}
