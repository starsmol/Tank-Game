package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage img = null;

    public Rectangle solidArea;

    //movement direction
    public boolean isMovingUp = false;
    public boolean isMovingDown = false;
    public boolean isMovingLeft = false;
    public boolean isMovingRight = false;


    //closions
    public boolean collisionOn = false;
    public boolean collisionLeft = false;
    public boolean collisionRight = false;
    public boolean collisionUp = false;
    public boolean collisionDown = false;
    public boolean isMoving = false;
}
