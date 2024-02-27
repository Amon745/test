import javax.imageio.ImageIO;
import java.io.IOException;

public class key extends SuperEquipmentExtras
{
    public key()
    {
        name="key";
        try
        {
            image= ImageIO.read(getClass().getResourceAsStream("/equipmentExtras/key.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
}
