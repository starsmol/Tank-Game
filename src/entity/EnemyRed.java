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

public class EnemyRed extends Entity implements Runnable {
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
    private Thread enemyThread;
    private volatile boolean running;

    public EnemyRed(GamePanel gp, Player player) {
        this.gp = gp;
        health = 100;
        this.x = 400;
        this.y = 400;
        this.speed = 2.0;
        this.mapTileNum = new int[gp.maxScreenRow][gp.maxScreenColumn];
        loadMap();
        this.fireSpeed = 30;
        dead = false;
        this.player = player;
        path = new ArrayList<int[]>();
        projectileM = new ProjectileMenager(gp);
        this.solver = new MazeSolver();

        running = true;
        enemyThread = new Thread(this, "EnemyRedThread");
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
    private void move()
    {int startX = this.x / gp.tileSize;
        int startY = this.y / gp.tileSize;
        int goalY = player.y / gp.tileSize;
        int goalX = player.x / gp.tileSize;



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
        }
        if(!dead) {
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
            g2.setColor(Color.RED);
            g2.fillRect(x, y, gp.tileSize, gp.tileSize);
            g2.setColor(Color.GREEN);
            int hp_width = gp.tileSize * this.health / 100;
            g2.fillRect(x, y, hp_width, gp.tileSize);

            projectileM.draw(g2);
        }
    }

    public void reset() {
        this.health = 100;                  // Pełne zdrowie
        this.dead = false;                  // Wróg znów "żyje"
        this.x = 400;                       // Pozycja startowa
        this.y = 400;
        this.updateCounter = 0;            // Reset licznika aktualizacji
        this.projectileM.clear();          // Wyczyść pociski
        this.targetX = player.x;           // Resetuj cele
        this.targetY = player.y;
        this.path.clear();                 // Wyczyść aktualną ścieżkę (jeśli używana)
    }

    public int getHealth() {
        return health;
    }
}
