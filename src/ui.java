import java.awt.*;
import java.awt.image.BufferedImage;

public class ui {
    Level1 gp;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = " ";
    int messageCount = 0;
    public boolean gameFinished = false;
     Font arial_80B;
     //playtime
     double playTime;
     BufferedImage health_full, health_half,health_blank; // stores location of images

    public ui(Level1 gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);//finish the intantiation before the game loop starts
        key key = new key();
        keyImage = key.image;
        SuperEquipmentExtras health = new health(gp);
        health_full=health.image;
        health_half=health.image2;
        health_blank=health.image3;
    }

    public void displayMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2)
    {
        if (gameFinished == true)
        {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String text;
            int textLength=0;
            int x;
            int y;

            text="well done treasure found";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); //returns length of message so its displayed in the center of the screen
            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2-(gp.tileSize*3); //finds center of screen minus text length
            g2.drawString(text,x,y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text="Congratulations";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2+(gp.tileSize*2); //finds center of screen
            g2.drawString(text,x,y);

            text="your time is:"+playTime +" seconds";
            textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth(); //returns length of message so its displayed in the center of the screen
            x=gp.screenWidth/2-textLength/2;
            y=gp.screenHeight/2+(gp.tileSize*4); //finds center of screen minus text length
            g2.drawString(text,x,y);


            gp.gameThread=null; //ends game loop
        }
        else // game is not finished continue to draw
        {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x  " + gp.player.playerkeys, 74, 65);  // i dont include font instance in the game loop as it takes up unneccessary memory of 60fps

          //  gp.player.life=3;
            //player health
            int x=170;//gp.tileSize/2;
            int y=40;//gp.tileSize/2;
            // while loop ised to update draw() of the hearts
            int i=0;
            while(i<gp.player.maxLife/2) //maxlife / 2 as 1 hole heart is 2 lifes
            {
                g2.drawImage(health_blank,x,y,null);
                i++;
                x+=gp.tileSize; // x is incremented to draw next image on the right
            }
            x=170;
            y=40;
            i=0; //reset
            //while loop used to draw current player lifes
           while(i<gp.player.life)
            {
                g2.drawImage(health_half,x,y,null);
                i++;
                if(i<gp.player.life) //if this is true replace health hearts with full hearts
                {
                    g2.drawImage(health_full,x,y,null);
                    i++;
                    x+=gp.tileSize;
                }
            }

            //time
            playTime+=(double)1/60; //draw method called 60 fps times so 1/60 seconds is a single iteration in every loop
            g2.drawString("time: "+playTime,gp.tileSize*11, 65);

            if (messageOn == true)
            {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
                messageCount++; // everytime draw() is called messageCoutn is incremetned
                if (messageCount >= 120) //120 fps is the time it will be displayed, game is set at 60fps so 120/60=2 seconds
                {
                    messageCount = 0; //when messageCounter reaches a certain number stop displaying text
                    messageOn = false;
                }
            }



        }
    }

}




