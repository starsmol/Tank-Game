package main;

import entity.Player;
import tiles.TileMangager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16 x 16 size
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48 x 48 tile size
    public final int maxScreenColumn = 29;
    public final int maxScreenRow = 18;
    final int SCREEN_WIDTH = maxScreenColumn * tileSize;
    final int SCREEN_HEIGHT = maxScreenRow * tileSize;

    //FPS
    int FPS = 60;

    TileMangager tileM = new TileMangager(this);
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    public CollisonChecker cChecker = new CollisonChecker(this);


    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update() {
        player.update();


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();

    }


}
