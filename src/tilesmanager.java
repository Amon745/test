import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class tilesmanager
{
    Level1 gp;
    tiles[] tile;
    int[][] terrainTileN; //2d array to display tiles for terrain
    public tilesmanager(Level1 gp)
    {
        this.gp=gp;
        tile = new tiles[10];
        getImage();
        terrainTileN=new int[gp.maxWorldCol][gp.maxWorldRow];
        loadTerrain();
    }
    public void getImage()
    {
        try
        {
            tile[0]=new tiles();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png")); // file location of tile images

            tile[3]=new tiles();
            tile[3].image= ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[5]=new tiles();
            tile[5].image= ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

            tile[4]=new tiles();
            tile[4].image= ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png")); // file location of tile images
            tile[4].collision=true;

            tile[1]=new tiles();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision=true;

            tile[2]=new tiles();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].collision=true;

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadTerrain()
    {
        try
        {
            InputStream is = getClass().getResourceAsStream("/terrain/Level1 terrain.txt"); // inputs text file to manage tiles
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // reads the text file
            int col=0;
            int row=0;   // changed maxscreencol to maxWorldcol to load entire terrain
            while(col< gp.maxWorldCol&&row< gp.maxWorldRow)
            {
                String line=br.readLine(); // reads single text line per iteratiom from the text file as a string

                while(col<gp.maxWorldCol)
                {
                    String[] numbers =line.split(" "); // reads all tile numbers  and puts them into 2d array
                int num=Integer.parseInt(numbers[col]); // converts the sting into ints so it can work in array
                     terrainTileN[col][row]=num; // stores tile numbers into 2d array
                     col++; // repeats untill all cols are filled in
                }
                if(col==gp.maxWorldCol) // whem all cols are filled then fill in the rows
                {
                    col=0;
                    row++;
                }
            }
            br.close();
        }

        catch(IOException e)
        {

        }
    }
public void draw(Graphics2D g2)
{


    int worldCol=0;
    int worldRow=0;


while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
{
    int tileNum=terrainTileN[worldCol][worldRow]; //tileNum is the index for the 2d array
    int worldx=worldCol*gp.tileSize;
    int worldy=worldRow*gp.tileSize; // used to find out x and y
    int screenx=worldx-gp.player.worldx+gp.player.screenx;
    int screeny=worldy-gp.player.worldy+gp.player.screeny;
   /* if(worldx+gp.tileSize>gp.player.worldx-gp.player.screenx &&
            worldx-gp.tileSize>gp.player.worldx+gp.player.screenx &&
            worldy+gp.tileSize>gp.player.worldy-gp.player.screeny &&
            worldy-gp.tileSize>gp.player.worldy+gp.player.screeny)
    {
        g2.drawImage(tile[tileNum].image, screenx, screeny, gp.tileSize, gp.tileSize, null); //draws all tiles
   }*/
    g2.drawImage(tile[tileNum].image, screenx, screeny, gp.tileSize, gp.tileSize, null);
    worldCol++; //increments and draws all tiles and gets them from getimage()


    if(worldCol==gp.maxWorldCol) // stops while untill all cols are filled in
    {
        worldCol=0;

        worldRow++; //starts to fill in the rows

    }

}

}

}
