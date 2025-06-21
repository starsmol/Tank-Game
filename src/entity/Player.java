package entity;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import projectile.Projectile;
import projectile.ProjectileMenager;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    public GamePanel gp;
    KeyHandler keyH;
    MouseHandler mouseH;
    public ProjectileMenager projectileM;
    int fireSpeed;
    int fireCount;
    int health;
    public  Player(GamePanel gp, KeyHandler keyH, MouseHandler mouseH) {
        this.gp = gp;
        this.keyH = keyH;
        this.mouseH = mouseH;
        projectileM = new ProjectileMenager(gp);
        setDefaultValues();
        getPlayerImage();
        this.fireSpeed = 5;
        this.fireCount = 0;
        this.health = 100;
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);

    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;

    }

    public void isHit()
    {
        this.health -= 10;
    }


    public void getPlayerImage()
    {
        try{
                img = ImageIO.read(new File("assets/player/player_tank_right_1.png"));


        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void clearMovementAndCollision() {
        collisionOn = false;
        collisionRight = false;
        collisionLeft = false;
        collisionUp = false;
        collisionDown = false;

        isMoving = false;
        isMovingUp = false;
        isMovingDown = false;
        isMovingLeft = false;
        isMovingRight = false;

    }
    public void update() {

        clearMovementAndCollision();

        if(keyH.upPressed == true) {
            isMovingUp = true;
        }
        if(keyH.downPressed == true) {
            isMovingDown = true;
        }
        if(keyH.leftPressed == true) {
            isMovingLeft = true;
        }
        if(keyH.rightPressed == true) {
            isMovingRight = true;
        }

        this.fireCount++;
        if(keyH.firePressed && gp.FPS / this.fireSpeed < this.fireCount )
        {
            int velX = mouseH.mouseX - this.x;
            int velY = mouseH.mouseY - this.y;
            boolean goingUp = (velY >= 0);
            boolean goingLeft = (velX >= 0);
            projectileM.add(this.x + solidArea.width/2, this.y + solidArea.height/2, velX/velY,goingUp, goingLeft, "Player");
            this.fireCount = 0;
        }


        gp.cChecker.checkTile(this);

        if(isMovingUp && !collisionUp) {
            y -= speed;
        }
        if(isMovingDown && !collisionDown) {
            y += speed;
        }
        if(isMovingLeft && !collisionLeft) {
            x -= speed;
        }
        if(isMovingRight && !collisionRight) {
            x += speed;
        }

        projectileM.update();

    }
    public void drawHealthBar(Graphics g2) {
        g2.setColor(Color.RED);
        g2.fillRect(x, y-8, gp.tileSize, 4);
        g2.setColor(Color.GREEN);
        int healthBarWidth = gp.tileSize * this.health / 100;
        g2.fillRect(x, y-8, healthBarWidth, 4);
    }

    public void draw(Graphics g2) {
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        image = img;
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        drawHealthBar(g2);
        projectileM.draw(g2);
    }

}
