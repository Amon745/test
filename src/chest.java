import javax.imageio.ImageIO;
import java.io.IOException;

public class chest extends SuperEquipmentExtras
{
    public chest()
    {
        name="chest";
        try
        {
            image= ImageIO.read(getClass().getResourceAsStream("/equipmentExtras/chest (OLD).png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
