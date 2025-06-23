package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    public int commandNum = 0;
    public final int maxCommandNum = 2;
    public final int resumeCommandNum = 0;
    public final int restartCommandNum = 1;
    public final int exitCommandNum = 2;

    public int titleCommandNumber = 1;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void drawPause(Graphics2D g2) {
        int x = gp.tileSize*10;
        int y = gp.tileSize*3;
        int width = gp.tileSize*9;
        int height = gp.tileSize*10;
        drawSubWindow(x, y, width, height, g2);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32f));
        int centerX = gp.tileSize*13;
        // draw inside box
        g2.drawString("Options", centerX, y+gp.tileSize);

        g2.drawString("Resume (P)", centerX, y+gp.tileSize*2);

        g2.drawString("Restart", centerX, y+gp.tileSize*3);

        g2.drawString("Exit", centerX, y+gp.tileSize*4);

        g2.drawString(">", centerX-25, y+gp.tileSize*(commandNum+2));

    }
    public void drawSubWindow(int x, int y, int width, int height, Graphics2D g2) {
        Color c = new Color(255,255,255);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(0,0,0, 200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.fillRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public void drawMainTitle(Graphics2D g2) {
        int x = gp.tileSize;
        int y = gp.tileSize;
        int width = gp.SCREEN_WIDTH-gp.tileSize*2;
        int height = gp.SCREEN_HEIGHT-gp.tileSize*2;
        drawSubWindow(x, y, width, height, g2);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(64f));
        int centerX = gp.tileSize*13;
        // draw inside box
        g2.drawString("Welcome in Tank Game!", x*6, y+gp.tileSize*3);

        g2.setFont(g2.getFont().deriveFont(48f));

        g2.drawString("Start", centerX, y+gp.tileSize*8);

        g2.drawString("Exit", centerX, y+gp.tileSize*10);

        g2.drawString(">", centerX-50, y+gp.tileSize*(titleCommandNumber*2+6));
    }

    public void drawEndGameScreen(Graphics2D g2) {
        int x = gp.tileSize;
        int y = gp.tileSize;
        int width = gp.SCREEN_WIDTH-gp.tileSize*2;
        int height = gp.SCREEN_HEIGHT-gp.tileSize*2;
        drawSubWindow(x, y, width, height, g2);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(64f));
        int centerX = gp.tileSize*13;
        // draw inside box
        g2.drawString("GameOver!", x*6, y+gp.tileSize*3);

        if (gp.player.getHealth() > 0){
            g2.drawString("You WON! :)", x*6, y+gp.tileSize*5);
        } else {
            g2.drawString("You lose :(", x*6, y+gp.tileSize*5);
        }


        g2.setFont(g2.getFont().deriveFont(48f));

        g2.drawString("Restart", centerX, y+gp.tileSize*8);

        g2.drawString("Exit", centerX, y+gp.tileSize*10);

        g2.drawString(">", centerX-50, y+gp.tileSize*(titleCommandNumber*2+6));
    }
}
