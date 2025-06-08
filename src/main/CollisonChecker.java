package main;

import entity.Entity;

public class CollisonChecker {

    GamePanel gp;
    public CollisonChecker(GamePanel gp) {
        this.gp = gp;
    }



    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.x;
        int entityRightWorldX = entity.x + gp.tileSize;
        int entityTopWorldY = entity.y;
        int entityBottomWorldY = entity.y + gp.tileSize;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        if(entity.isMovingUp) {
            entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
            if (gp.tileM.tiles[tileNum1].collison == true || gp.tileM.tiles[tileNum2].collison == true) {
                entity.collisionOn = true;
                entity.collisionUp = true;
            }
        }

        entityLeftCol = entityLeftWorldX/gp.tileSize;
        entityRightCol = entityRightWorldX/gp.tileSize;
        entityTopRow = entityTopWorldY/gp.tileSize;
        entityBottomRow = entityBottomWorldY/gp.tileSize;

        if(entity.isMovingDown) {
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
            if (gp.tileM.tiles[tileNum1].collison == true || gp.tileM.tiles[tileNum2].collison == true) {
                entity.collisionOn = true;
                entity.collisionDown = true;
            }
        }

        entityLeftCol = entityLeftWorldX/gp.tileSize;
        entityRightCol = entityRightWorldX/gp.tileSize;
        entityTopRow = entityTopWorldY/gp.tileSize;
        entityBottomRow = entityBottomWorldY/gp.tileSize;

        if(entity.isMovingLeft) {
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityLeftCol];
            if (gp.tileM.tiles[tileNum1].collison == true || gp.tileM.tiles[tileNum2].collison == true) {
                entity.collisionOn = true;
                entity.collisionLeft = true;
            }
        }

        entityLeftCol = entityLeftWorldX/gp.tileSize;
        entityRightCol = entityRightWorldX/gp.tileSize;
        entityTopRow = entityTopWorldY/gp.tileSize;
        entityBottomRow = entityBottomWorldY/gp.tileSize;

        if(entity.isMovingRight) {
            entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityBottomRow][entityRightCol];
            tileNum2 = gp.tileM.mapTileNum[entityTopRow][entityRightCol];
            if (gp.tileM.tiles[tileNum1].collison == true || gp.tileM.tiles[tileNum2].collison == true) {
                entity.collisionOn = true;
                entity.collisionRight = true;
            }
        }




    }

}
