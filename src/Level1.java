import javax.swing.*;
import java.awt.*;

public class Level1 extends JPanel implements Runnable{
    //screen
        final int originalTileSize = 16; //16x16 tile
        final int scale = 3;
        public final int tileSize = originalTileSize * scale; //48x48 tile
       public final int maxScreenCol = 16;
        public final int maxScreenRow = 12;
      public  final int screenWidth = tileSize * maxScreenCol; //768 pixels
      public  final int screenHeight = tileSize * maxScreenRow; //576 pixel
//for the terrain
    public final int maxWorldCol=50;
    public final int maxWorldRow=50; //terrain size
    public final int worldHeight=maxWorldRow*tileSize;
    public final int worldWidth=maxWorldCol*tileSize;
    int FPS=60;
    // contructors
        Keybinds KeyH =new Keybinds(); //constructor instantiates keybinds class
        Thread gameThread; //automatically calls methods and keeps programming running untill stopped
        collisionManager collisionM =new collisionManager(this); // instantiate collision class
       public  player player=new player(this,KeyH); // constructor instantiates player class
        tilesmanager manageTile =new tilesmanager(this);
        public SuperEquipmentExtras[] object =new SuperEquipmentExtras[10]; //instantiate 10 new chest objects
    public assetPlacer aPlacer=new assetPlacer(this);
    public ui UI=new ui(this);
    public eventManager eManager=new eventManager(this);
   // public mob monster[]=new mob[20]; //max num of monsters that can be displayed

        public Level1() //contructor method for level 1
        {
                this.setPreferredSize(new Dimension(screenWidth, screenHeight));
                this.setBackground(Color.black);
                this.setDoubleBuffered(true);
                this.addKeyListener(KeyH);
                this.setFocusable(true);

        }
        public void setUpAssets()
        {
            aPlacer.setObjects(); //call from assetPlacer class
           // aPlacer.setMonster();
        }
        public void startGameThread()
        {
              gameThread = new Thread(this); //passes the whole level 1 gamepanel into constructor (thread is instantiated)
                 gameThread.start();
        }
        @Override

        public void run()
        { /*public void run()
        {
                double drawInterval =1000000000/FPS; // 1 billion nana sec = one sec
                // screen is drawn 1 billion / 60 times=0.16666 so 60 times per second 60fps
                double nextDrawTime = System.nanoTime() + drawInterval;

                while(gameThread !=null) //this loop is the game loop used to update and repaint
                {
                        update();
                        repaint();

                        try {
                                double remainingTime=nextDrawTime=System.nanoTime();
                                remainingTime=remainingTime/1000000;
                                if(remainingTime<0){
                                        remainingTime=0;
                                }
                                Thread.sleep((long) remainingTime); //pauses the game loop and stops game from running
                                nextDrawTime+=drawInterval; //every 0.016666 secs later will be the next draw time
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
     }*/
                double drawInterval = 1000000000/FPS; // 1 billion nana sec = one sec

                // screen is drawn 1 billion / 60 times=0.16666 so 60 times per second 60fps
                double delta =0;
                long lastTime = System.nanoTime();
                long currentTime;

                while (gameThread != null) //this loop is the game loop used to update and repaint
                {
                        currentTime = System.nanoTime();
                        //System.out.println("current time "+currentTime);
                        delta +=(currentTime-lastTime)/drawInterval;
                        lastTime=currentTime;

                        if(delta>1)
                        {
                                update();
                                repaint();
                                delta--;
                        }
                }
        }
        public void update()
        {
                player.update();
             /*   for(int i=0; i< monster.length;i++)
                {
                    if(monster[i]!=null)
                    {
                        monster[i].update();
                    }
               }*/
        }
        //paint component is called everytime to update the graphics of the player
        public void paintComponent(Graphics g)
        {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
               manageTile.draw(g2); //draw background tiles before player
                for(int i=0;i<object.length;i++) // loops in all elements in 1d array objects
                {
                    if (object[i]!=null) // i include if validation to avoid null error from object array
                    {
                        object[i].draw(g2,this);
                    }
                }

           /* for(int i=0; i< monster.length;i++)
            {
                if(monster[i]!=null)
                {
                    monster[i].draw(g2,this);
                }
            }*/
            // draw the assets and equipment
                player.draw(g2);
                //ui
                UI.draw(g2);
            g2.dispose();
        }
}