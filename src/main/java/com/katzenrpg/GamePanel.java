package main.java.com.katzenrpg;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Willkommen im KatzenRPG!", 20, 20);
    }
}