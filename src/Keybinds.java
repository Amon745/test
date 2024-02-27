import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Keybinds implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean enterPressed;

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // gets keys in terms of numbers

       /* switch (code) {
            case KeyEvent.VK_W -> upPressed = true;
            case KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_D -> rightPressed = true;


            // Add more cases if needed for other keys

            default -> {
            }
            // Handle any other key events or remove this block if not needed
       */
        if(code==KeyEvent.VK_W){
            upPressed = true;
    }
        if(code==KeyEvent.VK_S){
            downPressed = true;
        }
        if(code==KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code==KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code==KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }


    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
      /*  switch (code) {
            case KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;


            // Add more cases if needed for other keys

            default -> {
            }
            // Handle any other key events or remove this block if not needed
*/
        if(code==KeyEvent.VK_W){
            upPressed = false;
        }
        if(code==KeyEvent.VK_S){
            downPressed = false;
        }
        if(code==KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code==KeyEvent.VK_D){
            rightPressed = false;
    }
        if(code==KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }
}