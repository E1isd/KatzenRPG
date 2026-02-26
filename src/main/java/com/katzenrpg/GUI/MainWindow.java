package main.java.com.katzenrpg.GUI;

import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("KatzenRPG");
        setSize(1920, 1080); // Auflösung 1920x1080 (Temporär, später anpassbar)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new GamePanel());
    }

    public void showWindow() {
        setVisible(true);
    }
}
