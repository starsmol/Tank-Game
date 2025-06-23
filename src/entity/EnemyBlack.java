package entity;

import main.GamePanel;
import main.MazeSolver;
import projectile.ProjectileMenager;
import tiles.Tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyBlack extends Entity implements Runnable {
    int health;
    boolean dead;
    public GamePanel gp;
    public ProjectileMenager projectileM;
    Player player;
    int fireSpeed;
    int updateCounter;
    public Tile[] tiles;
    public int mapTileNum[][];
    List<int[]> path;
    MazeSolver solver;

    private int targetX ;
    private int targetY ;
    private double speed = 2.0;
    private int randomiserTimer;
    Random rand = new Random();


    int goalY ;
    int goalX ;

    private Thread enemyThread;
    private volatile boolean running;

    public EnemyBlack(GamePanel gp, Player player) {
        this.gp = gp;
        health = 100;
        this.x = 10*gp.tileSize;
        this.y = 16*gp.tileSize;
        this.speed = 2;
        this.mapTileNum = new int[gp.maxScreenRow][gp.maxScreenColumn];
        loadMap();
        this.fireSpeed = 30;
        dead = false;
        this.player = player;
        path = new ArrayList<int[]>();
        projectileM = new ProjectileMenager(gp);
        this.solver = new MazeSolver();
        this.randomiserTimer = 0;

        int goalY = 2;
        int goalX = 2;

        running = true;
        enemyThread = new Thread(this, "EnemyBlackThread");
        enemyThread.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                if (gp.gameState == gp.playState) {
                    update();
                }
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopEnemy() {
        running = false;
        try {
            enemyThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        projectileM.add(this.x, this.y, (double) dX /dY, isGoingUp, isGoingLeft, "red");
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
            System.out.println("Loaded Map: "+mapTileNum[0].length);
            br.close();

        }catch (Exception e){

        }


    }

    private void randomiseDirection(){
        int x, y;
        y = rand.nextInt(gp.maxScreenRow);
        x = rand.nextInt(gp.maxScreenColumn);
        while (mapTileNum[y][x] == 0){
            y = rand.nextInt(gp.maxScreenRow);
            x = rand.nextInt(gp.maxScreenColumn);
        }
        this.goalX = x;
        this.goalY = y;

        System.out.println("Randomiser Goal X: "+this.goalX+" Goal Y: "+this.goalY);
    }

    private void move()
    {

        int startX = this.x / gp.tileSize;
        int startY = this.y / gp.tileSize;



        int dX = startX - goalX;
        int dY = startY - goalY;


        if( dX > 0 )
        {
            if ( mapTileNum[startY][(int) ((this.x - this.speed) / gp.tileSize)] == 1)
                this.x -= (int) this.speed;
        }
        if( dX < 0 )
        {
            if (mapTileNum[startY][(int) ((this.x + this.speed + this.gp.tileSize ) / gp.tileSize)] == 1)
                this.x += (int) this.speed;
        }
        if( dY > 0)
        {
            if ( mapTileNum[(((this.y - (int)this.speed))/gp.tileSize)][startX] == 1)
                this.y -= (int) this.speed;
        }
        if (dY < 0)
        {
            if ( mapTileNum[(((this.y + (int)this.speed)+this.gp.tileSize)/gp.tileSize)][startX] == 1)
                this.y += (int) this.speed;
        }}

    public void update() throws InterruptedException {
        this.updateCounter++;
        if (updateCounter >= 60){
            updateCounter = 0;
            this.randomiserTimer++;
        }
        if(!dead) {
            if (this.randomiserTimer >= 8){
                randomiseDirection();
                this.randomiserTimer = 0;
            }

            if (health <= 0)
                dead = true;

            targetX = player.x;
            targetY = player.y;

            if (this.updateCounter % fireSpeed == 0) {
                fireBullet();
            }
            if (this.updateCounter%2 == 0) {
                move();

            }

            projectileM.update();
        }
    }

    public void draw(Graphics g2) {
        if(!dead) {
            g2.setColor(Color.BLACK);
            g2.fillRect(x, y, gp.tileSize, gp.tileSize);
            g2.setColor(Color.GREEN);
            int hp_width = gp.tileSize * this.health / 100;
            g2.fillRect(x, y, hp_width, gp.tileSize);

            projectileM.draw(g2);
        }
    }

    public void reset() {
        this.health = 100;
        this.dead = false;
        this.x = 10 * gp.tileSize;
        this.y = 16 * gp.tileSize;
        this.updateCounter = 0;
        this.randomiserTimer = 0;
        this.projectileM.clear();     // Wymaga metody clear() w ProjectileMenager
        this.targetX = player.x;
        this.targetY = player.y;
        this.goalX = 2;
        this.goalY = 2;
        this.path.clear();
    }

    public int getHealth() {
        return health;
    }
}
