package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    public MenuFrame(String name) {
        setTitle("Winter Wonderland");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1122, 793);
        setResizable(false);
        setLocationRelativeTo(null);

        // Set background color
//        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color
        ImageIcon gifIcon = new ImageIcon("bg2menu.gif");
        JLabel gifLabel = new JLabel(gifIcon);

        // Set the content pane to the JLabel
        setContentPane(gifLabel);
        // Create a JLabel for the title
        JLabel titleLabel = new JLabel("Welcome " + name + " to Winter Wonderland!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton decorateButton = createButton();
        ImageIcon decorBtn = new ImageIcon("iconBTN/decorate.png");
        Image imageD1 = decorBtn.getImage();
        Image imageD2 = imageD1.getScaledInstance(300, 60, Image.SCALE_SMOOTH);
        decorateButton.setIcon(new ImageIcon(imageD2));

        JButton recipesButton = createButton();
        ImageIcon recipeBtn = new ImageIcon("iconBTN/recipe.png");
        Image imageR1 = recipeBtn.getImage();
        Image imageR2 = imageR1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        recipesButton.setIcon(new ImageIcon(imageR2));

        JButton songsButton = createButton();
        ImageIcon songsBtn = new ImageIcon("iconBTN/songs.png");
        Image imageS1 = songsBtn.getImage();
        Image imageS2 = imageS1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        songsButton.setIcon(new ImageIcon(imageS2));

        JButton exchangeButton = createButton();
        ImageIcon echBtn = new ImageIcon("iconBTN/manito.png");
        Image imageE1 = echBtn.getImage();
        Image imageE2 = imageE1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        exchangeButton.setIcon(new ImageIcon(imageE2));

        JButton rpgButton = createButton();
        ImageIcon gameBtn = new ImageIcon("iconBTN/adventure.png");
        Image imageG1 = gameBtn.getImage();
        Image imageG2 = imageG1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        rpgButton.setIcon(new ImageIcon(imageG2));

        // Adding ActionListeners to buttons
        decorateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DecorateFrame decorateFrame = new DecorateFrame(name);
                decorateFrame.setVisible(true);
            }
        });

        recipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RecipeMenuFrame recipesFrame = new RecipeMenuFrame(name);
                recipesFrame.setVisible(true);
            }
        });

        songsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SongsFrame songsFrame = new SongsFrame(name);
                songsFrame.setVisible(true);
            }
        });

        exchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ExchangeGiftsFrame exchangeFrame = new ExchangeGiftsFrame(name);
                exchangeFrame.setVisible(true);
            }
        });

        rpgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameFrame gameFrame= new GameFrame(name);
                gameFrame.setVisible(true);
            }
        });

        // Setting the layout and adding components
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 20, 0); // Adjusted top inset
        add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(decorateButton, gbc);

        gbc.gridy = 2;
        add(recipesButton, gbc);

        gbc.gridy = 3;
        add(songsButton, gbc);

        gbc.gridy = 4;
        add(exchangeButton, gbc);

        gbc.gridy = 5;
        add(rpgButton, gbc);
    }

    private JButton createButton() {
        JButton button = new JButton();
       // button.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        button.setPreferredSize(new Dimension(300, 50)); // Set preferred size
//        button.setBackground(new Color(109,113,46));
        return button;
    }
}
