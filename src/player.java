import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class player extends mob{
    Level1 gp;
    Keybinds keyH;
    public final int screenx; //draws player in correct tile of the screen
    public final int screeny;
    public int playerkeys=0; // keys in player inventory

    public player(Level1 gp, Keybinds keyH)
    {
        super(gp); // call contructor of super/parent class, mob is abtract class as it has no instance
        this.gp=gp;
        this.keyH=keyH;
        screenx=gp.screenWidth/2-(gp.tileSize/2); //returns halfway point of screen(where player will spawn)
        screeny=gp.screenHeight/2-(gp.tileSize/2);//-(gp.tileSize/2) refines player to real center of screen
        defualtPlayer();
        getPlayerImage();

        solidArea=new Rectangle(8,16,32,32); // collision area for player
        solidAreaDefaultx=solidArea.x;
        solidAreaDefaulty=solidArea.y; // i do this to get the x and y values so i can change the x and y values later

    }
public void defualtPlayer()
{

    worldx=gp.tileSize*23; //player position on world map set to the middle of screen
    worldy=gp.tileSize*21;
    speed=4;
    direction="down";
    //health
    maxLife=6; // 1 life = half a heart so 6 life = 3 hearts
    life=maxLife;
}
public void update()
{
    //whenever key is pressed by user input, keybinds class catches the input and the update method updates the coordinates and the paint component is called to draw the UI

    // when ever player presses a key then player moves by 4 pixels, whenever player hits the keys from keybonds class and the update Method updates the UI of the player movement
    if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed)
    {
        if(keyH.upPressed)
        {
            direction="up";

        }
        else if(keyH.downPressed)
        {
            direction="down";

        }
        else if(keyH.leftPressed)
        {
            direction="left";

        }
        else if(keyH.rightPressed)
        {
            direction="right";

        }
        //tile collision
        collisionOn=false;
        gp.collisionM.checkTile(this);
        //check object collision
        int objectid=gp.collisionM.checkObject(this,true);
        pickupObject(objectid);
        // for each specific object, when player hits it, the object will do something

        //check event
        gp.eManager.checkEvent();
        gp.KeyH.enterPressed=false;

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
public void pickupObject(int id)
{
    if(id!=999) // checks if object is in object 1d array
    {
       // gp.object[id]=null;  null pointer assigned to object interacted with
        String objectName=gp.object[id].name;
        switch (objectName) {
            case "key" -> {
                playerkeys++; //variable to store player collected keys is incremented
                gp.object[id] = null; //key deleted when player picks it up
               // System.out.println("key" + playerkeys); //outputs amount of keys player has rn
            gp.UI.displayMessage("well done u got a key"); //calls displayMessage() and passes a desired input text as a parameter
            }
            case "door" -> {
                if (playerkeys > 0) {
                    gp.object[id] = null;
                    playerkeys--;
                    gp.UI.displayMessage("door unlocked");
                }
                else
                {
                    gp.UI.displayMessage("you need a key");
                }
                System.out.println("key" + playerkeys);
            }
            case"chest" ->{ //when player opens chest the level is finished
                gp.UI.gameFinished=true;
            }
            case "boots" ->{
                speed+=2;
                gp.object[id]=null;
                gp.UI.displayMessage("power up speed boots");
            }
        }
    }
}
    public void draw(Graphics2D g2)
    {
       //g2.setColor(Color.white);
       // g2.fillRect(x,y,gp.tileSize,gp.tileSize);
       BufferedImage image = switch (direction) {
            case "up" -> up1;
            case "down" -> down1;
            case "left" -> left1;
            case "right" -> right1;
            default -> null;
        };                  //changed to screenx and y for player camera
        g2.drawImage(image, screenx,screeny, gp.tileSize,gp.tileSize,null); //null is image observer
    }
    public void getPlayerImage()
    {
        try
        {
            up1= ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
           // up2= ImageIO.read(getClass().getResourceAsStream(""));
            down1= ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
          //  down2= ImageIO.read(getClass().getResourceAsStream(""));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
           // left2= ImageIO.read(getClass().getResourceAsStream(""));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            //right2= ImageIO.read(getClass().getResourceAsStream(""));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
