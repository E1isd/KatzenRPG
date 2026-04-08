package main.java.com.katzenrpg.GUI;
import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("KatzenRPG");
       /*  setSize(1920, 1080); //Auflösung 1920x1080 (Temporär, später anpassbar) */
       GraphicsDevice device =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(new GamePanel(), BorderLayout.CENTER);
        device.setFullScreenWindow(this);
    }

    public void showWindow() {
        setVisible(true);
    }
}
