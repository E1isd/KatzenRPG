package main.java.com.katzenrpg.GUI;
import javax.swing.*;
import main.java.com.katzenrpg.scenes.BattleScene;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    BattleScene battleScene;
    private BufferedImage placeholderImage;
    
    public GamePanel() {
        setPreferredSize(new Dimension(1920, 1080));
        try {
            placeholderImage = ImageIO.read(new File("assets/images/placeholder320x180.png"));
            System.out.println("Image loaded successfully!");
        } catch (IOException e) {
            System.err.println("Failed to load image: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        if (placeholderImage != null) {
            g2D.drawImage(placeholderImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(16); // Ungefähr 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}