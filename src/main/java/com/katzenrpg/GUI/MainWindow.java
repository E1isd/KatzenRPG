package main.java.com.katzenrpg.GUI;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("KatzenRPG");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new GamePanel());
    }

    public void showWindow() {
        setVisible(true);
    }
}