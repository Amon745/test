import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Adventure Time Game");
        BackgroundPanel backgroundPanel=new BackgroundPanel("c:/untitled/Images/background.jpg","c:/untitled/Images/logo.png");
        setContentPane(backgroundPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton startButton=createStyledButton("Start",100,40);
        JButton settingsButton=createStyledButton("Settings",100,40);
        JButton exitButton=createStyledButton("Exit",100,40);

        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null,"Starting");
                dispose();
                new LevelSelectionScreen().setVisible(true);
            }
        }
        );

        settingsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new SettingsScreen().setVisible(true);
                JOptionPane.showMessageDialog(null, "Opening Settings");
            }
        });

        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc =new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets = new Insets(10,0,10,0);
        add(startButton,gbc);
        gbc.gridy++;
        add(settingsButton,gbc);
        gbc.gridy++;
        add(exitButton,gbc);
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text,int width, int height )
    {
        JButton button =new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16 ));
        button.setForeground(Color. WHITE);
        button.setBackground(new Color(70,130, 180));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(width, height ));


        button.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(100,149,237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color(70,130,180));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Custom JPanel class for displaying a background image and logo
    private static class BackgroundPanel extends JPanel {
        private Image backgroundImage;
        private Image logoImage;
        public BackgroundPanel(String backgroundImagePath,String logoImagePath) {
            this.backgroundImage =new ImageIcon(backgroundImagePath).getImage();
            this.logoImage=new ImageIcon(logoImagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            g.drawImage(backgroundImage,0,0, getWidth(),getHeight(),this);
            // Draw the logo image at the top-center
            int x= (getWidth() -logoImage.getWidth(this))/2;
            int y= 10; // Adjust the vertical position of the logo
            g.drawImage(logoImage,x,y,this);
        }
    }
}