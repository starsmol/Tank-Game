package projectile;

import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class ProjectileMenager {
    GamePanel gp;
    public ArrayList<BasicBullet> playerBullet;

    public ProjectileMenager(GamePanel gp) {
        this.gp = gp;
        playerBullet = new ArrayList<>();

    }
    public void add(int x, int y, double ratio,boolean goingUp, boolean goingLeft, String name) {
        BasicBullet bullet = new BasicBullet(x, y, ratio,goingUp,goingUp, name);
        playerBullet.add(bullet);
    }

    public void update() {
        for (BasicBullet bullet : playerBullet) {
            bullet.update();
        }

        for(int i = playerBullet.size() - 1; i >= 0; i--) {
            BasicBullet bullet = playerBullet.get(i);
            if(Math.abs(bullet.x) > 3000 || Math.abs(bullet.y) > 3000) {
                playerBullet.remove(bullet);
            }
        }

    }
    public void draw(Graphics g2) {
        for (BasicBullet bullet : playerBullet) {
            bullet.draw(g2);
        }
    }
}
