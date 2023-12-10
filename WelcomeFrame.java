import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {
        setTitle("Merry Christmas Greeting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 576);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create a JLabel to hold the GIF
        ImageIcon gifIcon = new ImageIcon("merry_christmas.gif");
        JLabel gifLabel = new JLabel(gifIcon);

        // Set the content pane to the JLabel
        setContentPane(gifLabel);

        // Create and add components
        JButton enterButton = new JButton("Enter Realm");
        enterButton.setBackground(Color.lightGray);
        enterButton.setForeground(Color.WHITE);
        enterButton.setFont(new Font("Arial", Font.BOLD, 18));

        // Create a JLabel for the welcome text
        JLabel welcomeLabel = new JLabel("Welcome to Winter Wonderland!");
//        welcomeLabel.setFont(new Font("Bigbone Font", Font.BOLD, 72));
        welcomeLabel.setFont(new Font("Harrington", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.WHITE);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter your name:");
                if (name != null && !name.isEmpty()) {
                    dispose(); // Close the current frame
                    MenuFrame christmasFrame = new MenuFrame(name);
                    christmasFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter your name.");
                }
            }
        });

        // Create a JPanel with a transparent background
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        // Center the button and welcomeLabel within the JPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 0, 0); // Adjusted top inset
        panel.add(welcomeLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0); // Adjusted top inset
        panel.add(enterButton, gbc);

        // Set the content pane to the JPanel
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }
}
