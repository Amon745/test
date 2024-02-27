import javax.imageio.ImageIO;
import java.io.IOException;

public class boots extends SuperEquipmentExtras
{
    public boots()
    {
        name="boots";
        try
        {
            image= ImageIO.read(getClass().getResourceAsStream("/equipmentExtras/boots.png"));

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
}
