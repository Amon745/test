import javax.imageio.ImageIO;
import java.io.IOException;

public class health extends SuperEquipmentExtras
{
    Level1 gp;
    public health(Level1 gp)
    {
        this.gp=gp;
        name="health";
        try
        {
            image= ImageIO.read(getClass().getResourceAsStream("/health/health_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/health/health_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/health/health_blank.png"));

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
}
