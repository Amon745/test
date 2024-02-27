public class assetPlacer
{
    Level1 gp;
    public assetPlacer(Level1 gp)
    {
        this.gp=gp;
    }
    public void setObjects()
    {
        gp.object[0]=new chest(); // chest is the child of the SuperEquipmentExtra parent class i intantiate one object in the array
        gp.object[0].worldx= 10*gp.tileSize;
        gp.object[0].worldy= 7*gp.tileSize; // location of object 0 chest in terrain text file

        gp.object[1]=new door();
        gp.object[1].worldx= 30*gp.tileSize;
        gp.object[1].worldy= 16*gp.tileSize;

        gp.object[2]=new key();
        gp.object[2].worldx= 38*gp.tileSize;
        gp.object[2].worldy= 8*gp.tileSize;

        gp.object[3]=new key();
        gp.object[3].worldx= 23*gp.tileSize;
        gp.object[3].worldy= 40*gp.tileSize;

        gp.object[7]=new key();
        gp.object[7].worldx= 23*gp.tileSize;
        gp.object[7].worldy= 8*gp.tileSize;

        gp.object[8]=new key();
        gp.object[8].worldx= 16*gp.tileSize;
        gp.object[8].worldy= 13*gp.tileSize;

        gp.object[4]=new door();
        gp.object[4].worldx= 10*gp.tileSize;
        gp.object[4].worldy= 11*gp.tileSize;

        gp.object[5]=new door();
        gp.object[5].worldx= 8*gp.tileSize;
        gp.object[5].worldy= 28*gp.tileSize;

        gp.object[6]=new door();
        gp.object[6].worldx= 12*gp.tileSize;
        gp.object[6].worldy= 22*gp.tileSize;

        gp.object[9]=new boots();
        gp.object[9].worldx= 37*gp.tileSize;
        gp.object[9].worldy= 42*gp.tileSize;



    }
  /*  public void setMonster()
    {
        gp.monster[0]=new slime(gp);
        gp.monster[0].worldx=gp.tileSize*23;
        gp.monster[0].worldy=gp.tileSize*36;

        gp.monster[1]=new slime(gp);
        gp.monster[1].worldx=gp.tileSize*23;
        gp.monster[1].worldy=gp.tileSize*37;
    }*/
}
