package Main.ui;

import Main.model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FeedbackPanel extends JPanel {

    private static final int WIDTH = 1750;
    private static final int HEIGHT = 700;
    private Font outputFont = new Font("Segoe Script", Font.BOLD, 36);

    public FeedbackPanel() {
        setMaximumSize(new Dimension(WIDTH,HEIGHT));
        setBackground(new Color(0,0,0,150));
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxlayout);

    }

    private void addLabels(ArrayList<String> storyLines) {
        for (String s : storyLines) {
            JLabel lineLabel = new JLabel();
            lineLabel.setText(s);
            lineLabel.setFont(outputFont);
            lineLabel.setForeground(new Color(255,255,255));
            lineLabel.setMinimumSize(new Dimension(WIDTH - 25, 50));
            lineLabel.setMaximumSize(new Dimension(WIDTH - 25, 50));
            lineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            lineLabel.setHorizontalAlignment(JLabel.CENTER);
            add(Box.createVerticalStrut(10));
            add(lineLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes everything from the panel and re-adds new JLabels based on the inputted string arraylist
    public void newLabels(ArrayList<String> storyLines) {
        removeAll();
        addLabels(storyLines);
    }


}
