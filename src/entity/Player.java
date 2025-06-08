package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public  Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();

        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);

    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;

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


    }

    public void draw(Graphics g2) {
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        image = img;

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }

}
