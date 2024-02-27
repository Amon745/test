import javax.imageio.ImageIO;
import java.io.IOException;

public class door extends SuperEquipmentExtras
{
    public door()
    {
        name="door";
        try
        {
            image= ImageIO.read(getClass().getResourceAsStream("/equipmentExtras/door.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        collision=true;
    }
}
