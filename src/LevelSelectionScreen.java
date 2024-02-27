import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelSelectionScreen extends JFrame {

    private boolean[] levelUnlocked = {true, false, false};
    private LevelButton[] levelButtons;

    public LevelSelectionScreen()
    {
        setTitle("Level selection screen");


        String backgroundImagePath = "c:/untitled/Images/background.jpg";
        setBackgroundImage(backgroundImagePath);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        levelButtons= new LevelButton[3];

        for(int i=0;i<levelButtons.length;i++)
        {
            levelButtons[i] = createLevelButton("Level " + (i + 1), "c:/untitled/Images/l" +(i+1) +".png",levelUnlocked[i]);
            setupButtonAction(levelButtons[i], i + 1);
        }

        setLayout(null);

        for (int i=0;i<levelButtons.length;i++)
        {
            levelButtons[i].setBounds(50 + i * 200,50,150,150);
            add(levelButtons[i]);
        }

        setLocationRelativeTo(null);
    }

    private void setBackgroundImage(String imagePath) {
        BackgroundPanel backgroundPanel =new BackgroundPanel(imagePath);
        setContentPane(backgroundPanel);
    }

    private void setupButtonAction(LevelButton button,int level) {
        button.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLevelButton(level);
            }
        });
    }

    private void handleLevelButton(int level) {
        if(levelUnlocked[level-1])
        {
            JOptionPane.showMessageDialog(null, "Starting Level"+level+ "!");
        if(level==1)
        {
            Level1 gamePanel = new Level1();
            JFrame window = new JFrame(); // Create a new JFrame for Level1
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("2D adventure");

            window.add(gamePanel);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);

            gamePanel.setUpAssets(); // Set up assets at the start of the game
            gamePanel.startGameThread();
        }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Level"+level+ " is locked, complete the previous level to unlock");
        }
    }

    private LevelButton createLevelButton(String text,String imagePath,boolean unlocked)
    {
        LevelButton button =new LevelButton(text,imagePath,unlocked);
        button.setFocusPainted(false);
        return button;
    }

    private static class BackgroundPanel extends JPanel
    {
        private Image backgroundImage;

        public BackgroundPanel(String backgroundImagePath)
        {
            this.backgroundImage =new ImageIcon(backgroundImagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(backgroundImage,0,0,getWidth(),getHeight(),this);
        }
    }

    private static class LevelButton extends JButton
    {
        private Image levelImage;

        public LevelButton(String text,String imagePath,boolean unlocked)
        {
            super(text);
            this.levelImage =new ImageIcon(imagePath).getImage();
            this.setEnabled(unlocked);
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(levelImage,0,0,getWidth(),getHeight(),this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->new LevelSelectionScreen().setVisible(true));
    }
}
