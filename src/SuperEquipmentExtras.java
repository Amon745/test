import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperEquipmentExtras
{
    public BufferedImage image, image2,image3;
    public String name;
    public boolean collision=false;
    public int worldx;
    public int worldy;
    public Rectangle solidArea =new Rectangle(0,0,48,48); // instantiates solid barrier around objects like in player
    public int solidAreaDefualtx=0;
    public int getSolidAreaDefualty=0;
    public void draw(Graphics2D g2, Level1 gp)
    {
        int screenx=worldx-gp.player.worldx+gp.player.screenx;
        int screeny=worldy-gp.player.worldy+gp.player.screeny;

       /* if(worldx+gp.tileSize>gp.player.worldx-gp.player.screenx &&
                worldx-gp.tileSize>gp.player.worldx+gp.player.screenx &&
                worldy+gp.tileSize>gp.player.worldy-gp.player.screeny &&
                worldy-gp.tileSize>gp.player.worldy+gp.player.screeny)
        {
            g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null); //draws all tiles
        } */
        g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
    }


}
