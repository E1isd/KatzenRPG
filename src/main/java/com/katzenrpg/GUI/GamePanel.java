package main.java.com.katzenrpg.GUI;

import javax.swing.*;

import main.java.com.katzenrpg.scenes.BattleScene;

import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

        Thread gameThread;
    BattleScene battleScene;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        //g.drawString("Willkommen im KatzenRPG!", 20, 20); Temporärer Text, später durch Spiellogik ersetzen
    }

    @Override
    public void run() {
        // TODO: Implement game loop
    }
}
