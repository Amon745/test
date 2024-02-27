// this class will be the super/ parent class for all sprites in the game including the player

import java.awt.*;
import java.awt.image.BufferedImage;

public class mob {
    public int worldx;
    public int worldy; // both used for managing game camera
public int speed;
public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
public Rectangle solidArea = new Rectangle(0,0,48,48);// class to create invisible rectangle to store data for all mobs
 public boolean collisionOn = false;
public int solidAreaDefaultx;
public int solidAreaDefaulty;
public String direction;
public String name;
public int actionLockCounter=0;
//health
    public int maxLife;
    public int life;
    Level1 gp;
    public mob(Level1 gp) {
        this.gp=gp;
    }

    public void update()
    {
        collisionOn=false;
        gp.collisionM.checkTile(this);
//copied from player class
        if(collisionOn==false) //player can only move if collision is not a solid barrier
        {
            switch (direction) { //moved from if statement into this validation
                case "up" -> worldy -= speed; //changes to world y so player is in camera
                case "down" -> worldy += speed;
                case "left" -> worldx -= speed;
                case "right" -> worldx += speed;
            }
        }
    }


}
