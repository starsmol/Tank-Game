package projectile;

import main.GamePanel;

import java.awt.*;

public class BasicBullet extends Projectile {
    public BasicBullet(int x, int y, double ratio, boolean goingUp, boolean goingLeft, String name) {
        this.x = x;
        this.y = y;
        this.speed = 15;
        double pom_y = 1.0;
        double pom_x = ratio;

        double length = Math.sqrt(pom_x * pom_x + pom_y * pom_y);
        double scale = speed / length;
        this.velX = (int) (pom_x * scale);
        this.velY = (int) (pom_y * scale);
        if (!goingLeft) {this.velX *= -1;}
        if (!goingUp) {this.velY *= -1;}

        this.name = name;
        radius = 6;
    }

    public void update() {
        //pos update
        this.x += this.velX;
        this.y += this.velY;
    }
    public void draw(Graphics g2) {
        g2.setColor(Color.WHITE);
        g2.fillOval(this.x, this.y, 2 * radius, 2 * radius);
    }
}
