public class collisionManager
{
    Level1 gp;
    public collisionManager(Level1 gp )
    {
        this.gp = gp;
    }
    //checkTile() doesnt use intersects method as it would have to check every tile in the world terrain which would take up too much memory
    public void checkTile(mob mob)
    {
        // detect collisions complicated part
        int mobLeftWorldx=mob.worldx+mob.solidArea.x;
        int mobRightWorldx=mob.worldx+mob.solidArea.x+mob.solidArea.width;
        int mobTopWorldy=mob.worldy+mob.solidArea.y;
        int mobBottomWorldY=mob.worldy+mob.solidArea.y+mob.solidArea.height;
        // based on these coordinates, i find the col and row values
        int mobLeftCol=mobLeftWorldx/ gp.tileSize;
        int mobRightCol=mobRightWorldx/gp.tileSize;
        int mobTopRow=mobTopWorldy/gp.tileSize;
        int mobBottomRow=mobBottomWorldY/gp.tileSize;

        int tileN1;
        int tileN2; // only need to check 2 tiles when player collides into them

        switch (mob.direction) {
            case "up" -> {
                mobTopRow = (mobTopWorldy - mob.speed) / gp.tileSize;
                tileN1 = gp.manageTile.terrainTileN[mobLeftCol][mobTopRow]; //checks tile 1 colliding with player
                tileN2 = gp.manageTile.terrainTileN[mobRightCol][mobTopRow]; //checks tile 1 colliding with player
                if (gp.manageTile.tile[tileN1].collision || gp.manageTile.tile[tileN2].collision) {
                    // if tile1 or tile2 are collided into by player, then player is hitting a solid tile and a barrier
                    mob.collisionOn = true;
                }
            }
            case "down" -> {
                mobBottomRow = (mobBottomWorldY + mob.speed) / gp.tileSize;
                tileN1 = gp.manageTile.terrainTileN[mobLeftCol][mobBottomRow]; //checks tile 1 colliding with player
                tileN2 = gp.manageTile.terrainTileN[mobRightCol][mobBottomRow]; //checks tile 1 colliding with player
                if (gp.manageTile.tile[tileN1].collision || gp.manageTile.tile[tileN2].collision) {
                    // if tile1 or tile2 are collided into by player, then player is hitting a solid tile and a barrier
                    mob.collisionOn = true;
                }
            }
            case "left" -> {
                mobLeftCol = (mobLeftWorldx - mob.speed) / gp.tileSize;
                tileN1 = gp.manageTile.terrainTileN[mobLeftCol][mobTopRow]; //checks tile 1 colliding with player
                tileN2 = gp.manageTile.terrainTileN[mobLeftCol][mobBottomRow]; //checks tile 1 colliding with player
                if (gp.manageTile.tile[tileN1].collision || gp.manageTile.tile[tileN2].collision) {
                    // if tile1 or tile2 are collided into by player, then player is hitting a solid tile and a barrier
                    mob.collisionOn = true;
                }
            }
            case "right" -> {
                mobRightCol = (mobRightWorldx + mob.speed) / gp.tileSize;
                tileN1 = gp.manageTile.terrainTileN[mobRightCol][mobTopRow]; //checks tile 1 colliding with player
                tileN2 = gp.manageTile.terrainTileN[mobRightCol][mobBottomRow]; //checks tile 1 colliding with player
                if (gp.manageTile.tile[tileN1].collision || gp.manageTile.tile[tileN2].collision) {
                    // if tile1 or tile2 are collided into by player, then player is hitting a solid tile and a barrier
                    mob.collisionOn = true;
                }
            }
        }
    }
    public int checkObject(mob mob, boolean player) //used to check if player collides into an object and if player does it gets the index location of the object
    {
        int id=999;
        for(int i=0;i<gp.object.length;i++)
        {
            if(gp.object[i]!=null)
            {
                // if objects are not null then get the mob position
                // and get the solid area position
                mob.solidArea.x=mob.worldx+mob.solidArea.x;
                mob.solidArea.y=mob.worldy+mob.solidArea.y; //gets mob position
                gp.object[i].solidArea.x=gp.object[i].worldx+gp.object[i].solidArea.x; //gets object barrier position
                gp.object[i].solidArea.y=gp.object[i].worldy+gp.object[i].solidArea.y;

                switch (mob.direction) {
                    case "up" -> {
                        mob.solidArea.y -= mob.speed; //same concept as player collider

                        // Rectangle class has the intersects method
                        if (mob.solidArea.intersects(gp.object[i].solidArea)) // intersects method automatically check if two rectangles are colliding or not
                        // before i can use intersects i need to find out the position of the rectangles or solidArea positions
                        {
                          //  System.out.println("up collision");
                            if(gp.object[i].collision==true) // check if object is a barrier
                            {
                                mob.collisionOn=true; // stops mob from moving
                            }
                            if(player==true)
                            {
                                id=i; //if player collsion is true get the index id but if its not a player and its a npc then it doesnt do anything (need to pick up chest)
                            }
                        }
                    }
                    case "down" -> {
                        mob.solidArea.y += mob.speed;
                        if (mob.solidArea.intersects(gp.object[i].solidArea)) {
                         //   System.out.println("down collision");
                            if(gp.object[i].collision==true)
                            {
                                mob.collisionOn=true;
                            }
                            if(player==true)
                            {
                                id=i; //if player collsion is true get the index id but uf uts not a player and its a npc then it doesnt do anything (need to pick up chest)
                            }
                        }
                    }
                    case "left" -> {
                        mob.solidArea.x -= mob.speed;
                        if (mob.solidArea.intersects(gp.object[i].solidArea)) {
                          // System.out.println("left collision");
                            if(gp.object[i].collision==true)
                            {
                                mob.collisionOn=true;
                            }
                            if(player==true)
                            {
                                id=i; //if player collsion is true get the index id but uf uts not a player and its a npc then it doesnt do anything (need to pick up chest)
                            }
                        }
                    }
                    case "right" -> {
                        mob.solidArea.x += mob.speed;
                        if (mob.solidArea.intersects(gp.object[i].solidArea)) {
                            //System.out.println("right collision");
                            if(gp.object[i].collision==true)
                            {
                                mob.collisionOn=true;
                            }
                            if(player==true)
                            {
                                id=i; //if player collision is true get the index id but uf uts not a player and its a npc then it doesnt do anything (need to pick up chest)
                            }
                        }
                    }
                }
                // i need to reset the mob and object rectangle solid area after it is found as the for loop will keep iterating causing a crash
                //reset x and y afer the switch statement
                mob.solidArea.x=mob.solidAreaDefaultx;
                mob.solidArea.y=mob.solidAreaDefaulty;
                gp.object[i].solidArea.x=gp.object[i].solidAreaDefualtx;
                gp.object[i].solidArea.y=gp.object[i].getSolidAreaDefualty;
            }
        }
        return id;
    }
}
