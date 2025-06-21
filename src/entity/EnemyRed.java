package entity;

import main.GamePanel;
import projectile.BasicBullet;
import projectile.Projectile;
import projectile.ProjectileMenager;

import java.awt.*;

public class EnemyRed extends Entity {
    int health;
    boolean dead;
    public GamePanel gp;
    public ProjectileMenager projectileM;
    Player player;
    int fireSpeed;
    int fireCount;

    public EnemyRed(GamePanel gp, Player player) {
        this.gp = gp;
        health = 100;
        this.x = 400;
        this.y = 400;
        this.fireSpeed = 2;
        dead = false;
        this.player = player;

        projectileM = new ProjectileMenager(gp);

    }
    public void isHit()
    {
        this.health -= 10;
    }
    private void fireBullet()
    {
        int dX = this.x - player.x;
        int dY = this.y - player.y;
        boolean isGoingUp = (dY <= 0);
        boolean isGoingLeft = (dX <= 0);
        projectileM.add(this.x, this.y, dX/dY, isGoingUp, isGoingLeft, "red");
    }
    public void update()
    {
        if(!dead) {
            if (health <= 0)
                dead = true;

            this.fireCount++;
            if (this.gp.FPS / this.fireSpeed < fireCount) {
                fireBullet();
                this.fireCount = 0;
            }

            projectileM.update();
        }
    }

    public void draw(Graphics g2) {
        if(!dead) {
            g2.setColor(Color.RED);
            g2.fillRect(x, y, gp.tileSize, gp.tileSize);
            g2.setColor(Color.GREEN);
            int hp_width = gp.tileSize * this.health / 100;
            g2.fillRect(x, y, hp_width, gp.tileSize);

            projectileM.draw(g2);
        }
    }
}
