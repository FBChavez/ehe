package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class WelcomeFrame extends JFrame {
    private Set<String> namesSet;

    public WelcomeFrame() {
        setTitle("Merry Christmas Greeting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1122, 793);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create a JLabel to hold the GIF
        ImageIcon gifIcon = new ImageIcon("Photos/bg.gif");
        JLabel gifLabel = new JLabel(gifIcon);

        // Set the content pane to the JLabel
        setContentPane(gifLabel);

        // Create and add components
        JButton enterButton = new JButton("Enter Christmas Realm");
        enterButton.setBackground(new Color(226,217,202));
        enterButton.setForeground(new Color(88,66,63));
        enterButton.setFont(new Font("Arial", Font.BOLD, 18));
        enterButton.setPreferredSize(new Dimension(250, 50));

        // Create a JLabel for the welcome text
        //JLabel welcomeLabel = new JLabel("Welcome to Winter Wonderland!");
//        welcomeLabel.setFont(new Font("Bigbone Font", Font.BOLD, 72));
//        welcomeLabel.setFont(new Font("Harrington", Font.BOLD, 48));
//        welcomeLabel.setForeground(Color.WHITE);

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter your name:");
                //check if name is not a number
                try {
                    if (name != null && name.matches("-?\\d+(\\.\\d+)?"))
                        throw new IllegalArgumentException("Your name is not a number!");
                    if (name != null && !name.isEmpty()) {
                        dispose(); // Close the current frame
                        MenuFrame christmasFrame = new MenuFrame(name);
                        christmasFrame.setVisible(true);
                        if (!namesSet.contains(name)) appendNewUsers(name);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter your name.");
                    }
                } catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        //set nameSet
        namesSet = new HashSet<>();

        //get users from file
        getUsers();

        // Create a JPanel with a transparent background
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        // Center the button and welcomeLabel within the JPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 0, 0); // Adjusted top inset
//        panel.add(welcomeLabel, gbc);

//        gbc.gridy = 1;
//        gbc.insets = new Insets(20, 0, 0, 0); // Adjusted top inset
        panel.add(enterButton, gbc);

        // Set the content pane to the JPanel
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    //private ang pag store sa names or pag search if user is already a user (ha)?
    //useful for secret santa ni hihi
    private void appendNewUsers(String name){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("names.txt", true))) {
            writer.write(name);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getUsers(){
        try (BufferedReader reader = new BufferedReader(new FileReader("names.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                namesSet.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
