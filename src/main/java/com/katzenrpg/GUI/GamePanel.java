package main.java.com.katzenrpg.GUI;
import javax.swing.*;
import main.java.com.katzenrpg.scenes.BattleScene;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    private static final int BASE_WIDTH = 320;
    private static final int BASE_HEIGHT = 180;
    private static final int BUTTON_BASE_WIDTH = 120;
    private static final int BUTTON_BASE_HEIGHT = 54;

    Thread gameThread;
    BattleScene battleScene;
    private BufferedImage placeholderImage;
    private BufferedImage gameBackgroundImage;
    private BufferedImage startButtonImage;
    private JButton startButton;
    private boolean gameStarted = false;
    
    public GamePanel() {
        setPreferredSize(new Dimension(1920, 1080));
        setLayout(new GridBagLayout());
        loadBackgroundImages();
        createStartButton();
    }

    private void loadBackgroundImages() {
        placeholderImage = loadImage("assets/images/placeholder320x180.png");
        gameBackgroundImage = loadImage("assets/images/start_background.png");
        startButtonImage = loadImage("assets/images/button.png");
        if (gameBackgroundImage == null) {
            gameBackgroundImage = createFallbackImage(new Color(20, 40, 90), "Game Background");
        }
    }

    private BufferedImage loadImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            System.out.printf("Loaded image: %s\n", path);
            return image;
        } catch (IOException e) {
            System.err.printf("Failed to load image '%s': %s\n", path, e.getMessage());
            return null;
        }
    }

    private void createStartButton() {
        startButton = new JButton();
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> {
            gameStarted = true;
            startButton.setVisible(false);
            repaint();
        });

        if (startButtonImage != null) {
            startButton.setIcon(new ImageIcon(getScaledButtonImage()));
        } else {
            startButton.setText("Start");
            startButton.setFont(new Font("Arial", Font.BOLD, 32));
            startButton.setBackground(new Color(0, 120, 0));
            startButton.setForeground(Color.WHITE);
            startButton.setOpaque(true);
            startButton.setContentAreaFilled(true);
        }

        add(startButton, new GridBagConstraints());
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateButtonSize();
            }
        });
        updateButtonSize();
    }

    private void updateButtonSize() {
        int width = (int) (BUTTON_BASE_WIDTH * Math.min(getWidth() / (double) BASE_WIDTH, getHeight() / (double) BASE_HEIGHT));
        int height = (int) (BUTTON_BASE_HEIGHT * Math.min(getWidth() / (double) BASE_WIDTH, getHeight() / (double) BASE_HEIGHT));
        width = Math.max(80, width);
        height = Math.max(40, height);
        startButton.setPreferredSize(new Dimension(width, height));
        if (startButtonImage != null) {
            startButton.setIcon(new ImageIcon(getScaledButtonImage(width, height)));
        }
        revalidate();
    }

    private Image getScaledButtonImage() {
        int width = (int) (BUTTON_BASE_WIDTH * Math.min(getWidth() / (double) BASE_WIDTH, getHeight() / (double) BASE_HEIGHT));
        int height = (int) (BUTTON_BASE_HEIGHT * Math.min(getWidth() / (double) BASE_WIDTH, getHeight() / (double) BASE_HEIGHT));
        width = Math.max(80, width);
        height = Math.max(40, height);
        return getScaledButtonImage(width, height);
    }

    private Image getScaledButtonImage(int width, int height) {
        return startButtonImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        BufferedImage currentBackground = gameStarted ? gameBackgroundImage : placeholderImage;
        if (currentBackground != null) {
            g2D.drawImage(currentBackground, 0, 0, getWidth(), getHeight(), this);
        } else {
            g2D.setColor(Color.BLACK);
            g2D.fillRect(0, 0, getWidth(), getHeight());
        }
    }
    
    private BufferedImage createFallbackImage(Color baseColor, String text) {
        BufferedImage fallback = new BufferedImage(320, 180, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = fallback.createGraphics();
        g2D.setColor(baseColor);
        g2D.fillRect(0, 0, fallback.getWidth(), fallback.getHeight());
        g2D.setColor(Color.WHITE);
        g2D.setFont(new Font("Arial", Font.BOLD, 18));
        FontMetrics fm = g2D.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textX = (fallback.getWidth() - textWidth) / 2;
        int textY = (fallback.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2D.drawString(text, textX, textY);
        g2D.dispose();
        return fallback;
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