package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileMangager {

    GamePanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];


    public TileMangager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        this.mapTileNum = new int[gp.maxScreenRow][gp.maxScreenColumn];
        getTileImage();

        loadMap();
    }

    public void getTileImage(){

        try{
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(new File("assets/tiles/wall.jpg"));
            tiles[0].collison = true;

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(new File("assets/tiles/grass.jpg"));



        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try {

            BufferedReader br = new BufferedReader(new FileReader("assets/maps/map1"));

            int row = 0;
            int col = 0;

            for (row = 0; row < gp.maxScreenRow; row++){

                String line = br.readLine();

                for (col = 0; col < gp.maxScreenColumn; col++){

                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[col]);

                    mapTileNum[row][col] = number;
                }
            }
            System.out.println("po");
            br.close();

        }catch (Exception e){

        }


    }

    public void draw(Graphics2D g2){
        int row = 0;
        int col = 0;

        int x = 0;
        int y = 0;

        for (row = 0; row < gp.maxScreenRow; row++){
            for (col = 0; col < gp.maxScreenColumn; col++){
                int number = mapTileNum[row][col];
                g2.drawImage(tiles[number].image, x, y, gp.tileSize, gp.tileSize, null);
                x += gp.tileSize;
            }
            y += gp.tileSize;
            x = 0;
        }



    }

}
