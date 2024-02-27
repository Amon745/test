import java.awt.*;

public class eventManager
{
Level1 gp;
Rectangle eventRect;
int eventRectdefualtx;
int eventRectdefualty;
public eventManager(Level1 gp)
{
    this.gp=gp;
    eventRect=new Rectangle();
    eventRect.x=23;
    eventRect.y=23; //size of barrier of objects
    eventRect.height=2; //2by2 pixels, player interacts with object if in the middle of tile not the edge
    eventRect.width=2;
    eventRectdefualtx=eventRect.x;
    eventRectdefualty=eventRect.y;
}
public void checkEvent()
{
if(hit(30, 16, "right")) //pass in parameters from boolean hit
{
    //damagePit();
    teleport();
}
if(hit(23,7,"up"))
{
    healingPool();
}
if(hit(16,13,"left"))
{
    damagePit();
}
}

public boolean hit(int eventCol, int eventRow,String requiredDirection) // checks object collision
{
    //parameters will be passed from checkEvent() method
    boolean hit = false;
    gp.player.solidArea.x=gp.player.worldx+gp.player.solidArea.x;
    gp.player.solidArea.y=gp.player.worldy+gp.player.solidArea.y;//gets player position
    eventRect.x=eventCol*gp.tileSize+eventRect.x;
    eventRect.y=eventRow*gp.tileSize+eventRect.y;//gets players rectangle barrier position

    if(gp.player.solidArea.intersects(eventRect)) // i use intersects again to check collision
    {
        if(gp.player.direction.contentEquals(requiredDirection)||requiredDirection.contentEquals("any"))
        {
            hit=true; // if player rectangle collides with object rectangle then hit is returned to true
        }
    }
    gp.player.solidArea.x=gp.player.solidAreaDefaultx;
    gp.player.solidArea.y=gp.player.solidAreaDefaulty;
    eventRect.x=eventRectdefualtx;
    eventRect.y=eventRectdefualty;
    return hit;
}

public void damagePit()
{
    gp.UI.displayMessage("Oof got hit by trap");
    gp.player.life-=1;
}
 public void healingPool()
 {
     if(gp.KeyH.enterPressed==true)
     {
         gp.UI.displayMessage(" u drink the regeneration pool ");
         gp.player.life=gp.player.maxLife;
     }

 }
 public void teleport()
 {
     gp.UI.displayMessage("shortcut found teleported!!");
     gp.player.worldx=gp.tileSize*37;
     gp.player.worldy=gp.tileSize*10;
 }

}
