import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class SettingsScreen extends JFrame {

    private static final int MAX_SIZE=100;
    private JSlider[] sliders;
    private JCheckBox[] checkBoxes;
    private JButton[] buttons;
    private Stack<SettingsState> undoStack, redoStack;

    public SettingsScreen() {
        // Setting up the window
        setTitle("Settings");
        setSize(400,300);
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE);
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        sliders = new JSlider[2];
        checkBoxes = new JCheckBox[2];
        buttons= new JButton[4];

        undoStack = new Stack<>();
        redoStack =new Stack<>();

        sliders[0]=createSlider("Volume", 0, 100, 50);
        sliders[1]=createSlider("Brightness", 0, 100, 50);

        for(JSlider slider:sliders)
        {
            settingsPanel.add(slider);
        }

        checkBoxes[0]=createCheckBox("Vsync");
        checkBoxes[1] =createCheckBox("Fullscreen");

        for(JCheckBox checkBox:checkBoxes)
        {
            settingsPanel.add(checkBox);
        }

        buttons[0]= createButton("Apply",e -> applySettings());
        buttons[1]= createButton("Undo",e -> undo());
        buttons[2]= createButton("Redo",e -> redo());
        buttons[3]= createButton("Cancel",e -> dispose());

        for(JButton button:buttons)
        {
            settingsPanel.add(button);
        }

        add(settingsPanel);
        setLocationRelativeTo(null);
    }

    private JSlider createSlider(String label,int min,int max,int initial)
    {
        JSlider slider = new JSlider(min, max, initial);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createTitledBorder(label));
        return slider;
    }

    private JCheckBox createCheckBox(String label)
    {
        JCheckBox checkBox = new JCheckBox(label);
        checkBox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        return checkBox;
    }

    private JButton createButton(String text, ActionListener action)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 40));

        // Add some color change when the mouse hovers over the button
        button.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color (100,149,237));
            }

            public void mouseExited(java.awt.event.MouseEvent evt)
            {
                button.setBackground(new Color (70,130,180));
            }
        });
        button.addActionListener(action);
        return button;
    }

    private void applySettings()
    {
        SettingsState currentState = new SettingsState(sliders[0].getValue(), sliders[1].getValue(),
                checkBoxes[0].isSelected(), checkBoxes[1].isSelected());

        if(undoStack.size() >= MAX_SIZE)
        {
            JOptionPane.showMessageDialog(null, "Too many changes, cannot apply new settings.");
        }
        else
        {
            undoStack.push(currentState);
            redoStack.clear();
            JOptionPane.showMessageDialog(null, "Settings Applied:\nVolume: " + currentState.getVolume()
                    + "\nBrightness: " + currentState.getBrightness()
                    + "\nVsync: " + currentState.isVsync()
                    + "\nFullscreen: " + currentState.isFullscreen());
        }
    }

    private void undo()
    {
        if(undoStack.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Nothing to undo");
        }
        else
        {
            SettingsState previousSetting =undoStack.pop();
            redoStack.push(previousSetting);
            setSettings(previousSetting);
            JOptionPane.showMessageDialog(null,"undoed");
        }
    }

    private void redo()
    {
        if(redoStack.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Nothing to redo");
        }
        else
        {
            SettingsState previousSetting =redoStack.pop();
            undoStack.push(previousSetting);
            setSettings(previousSetting);
            JOptionPane.showMessageDialog(null,"Redoed");
        }
    }

    private void setSettings(SettingsState state)
    {
        sliders[0].setValue(state.getVolume());
        sliders[1].setValue(state.getBrightness());
        checkBoxes[0].setSelected(state.isVsync());
        checkBoxes[1].setSelected(state.isFullscreen());
    }

    private static class SettingsState
    {
        private final int volume,brightness;
        private final boolean vsync,fullscreen;
        public SettingsState(int volume,int brightness,boolean vsync,boolean fullscreen)
        {
            this.volume = volume;
            this.brightness = brightness;
            this.vsync = vsync;
            this.fullscreen = fullscreen;
        }
        public int getVolume()
        {
            return volume;
        }
        public int getBrightness()
        {
            return brightness;
        }
        public boolean isVsync() {
            return vsync;
        }
        public boolean isFullscreen()
        {
            return fullscreen;
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> new SettingsScreen().setVisible(true));
    }
}
