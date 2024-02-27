import javax.swing.*;

public class Main {
    public static void main(String[] args) {

      /*  System.out.println("game starting");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D adventure");

        Level1 gamePanel = new Level1();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpAssets(); // want to set up assets at the start of the game
        gamePanel.startGameThread();
*/
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
            });
    }

}