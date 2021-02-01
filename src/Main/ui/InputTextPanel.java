package Main.ui;

import Main.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class InputTextPanel extends JPanel {

    private static final int WIDTH = 550;
    private static final int HEIGHT = 125;

    public InputTextPanel() {
        setBackground(new Color(0,0, 0));
        setMaximumSize(new Dimension(WIDTH,HEIGHT));
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxlayout);

    }



}