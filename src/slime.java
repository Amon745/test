import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class slime extends mob {
    public slime(Level1 gp) {
        super(gp);
        name = "slime";
        speed = 1; // slimes are slow
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultx = solidArea.x;
        solidAreaDefaulty = solidArea.y;
    }

    public void getImage() { //same as player one
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_up_1"));
            // up2= ImageIO.read(getClass().getResourceAsStream(""));
            down1 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_down_1"));
            //  down2= ImageIO.read(getClass().getResourceAsStream(""));
            left1 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_left_1"));
            // left2= ImageIO.read(getClass().getResourceAsStream(""));
            right1 = ImageIO.read(getClass().getResourceAsStream("/monster/slime_right_1"));
            //right2= ImageIO.read(getClass().getResourceAsStream(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) //simple AI
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //generates random number 1 to100
            if(i<=25)
            {
                direction = "up";
            }
            if(i>25&&i<=50)
            {
                direction = "down";
            }
            if(i>50 &&i<=75)
            {
                direction = "left";
            }
            if(i>75&&i<=100)
            {
                direction = "right";
            }
        }
        actionLockCounter = 0;
    }
public void draw(Graphics2D g2, Level1 gp)
{
    int screenx=worldx-gp.player.worldx+gp.player.screenx;
    int screeny=worldy-gp.player.worldy+gp.player.screeny;
    BufferedImage image=switch(direction)
            {
                case "up" -> up1;
                case "down" -> down1;
                case "left" -> left1;
                case "right" -> right1;
                default -> null;
            };
    g2.drawImage(image,screenx,screeny,gp.tileSize,gp.tileSize,null);
}


}