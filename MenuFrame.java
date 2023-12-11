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

        // Create buttons with equal size

        //JButton decorateButton = createButton("Decorate Virtual Christmas Tree");
        JButton decorateButton = createButton();
        ImageIcon decorBtn = new ImageIcon("iconBTN/decorate.png");
        Image im1 = decorBtn.getImage();
        Image im2 = im1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        decorateButton.setIcon(new ImageIcon(im2));

        JButton recipesButton = createButton();
        ImageIcon recipeBtn = new ImageIcon("iconBTN/recipe.png");
        Image r1 = recipeBtn.getImage();
        Image r2 = r1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        recipesButton.setIcon(new ImageIcon(r2));

        JButton songsButton = createButton();
        ImageIcon songsBtn = new ImageIcon("iconBTN/songs.png");
        Image s1 = songsBtn.getImage();
        Image s2 = s1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        songsButton.setIcon(new ImageIcon(s2));

        JButton exchangeButton = createButton();
        ImageIcon echBtn = new ImageIcon("iconBTN/manito.png");
        Image e1 = echBtn.getImage();
        Image e2 = e1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        exchangeButton.setIcon(new ImageIcon(e2));

        JButton rpgButton = createButton();
        ImageIcon gameBtn = new ImageIcon("iconBTN/adventure.png");
        Image g1 = gameBtn.getImage();
        Image g2 = g1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        rpgButton.setIcon(new ImageIcon(g2));
//        JButton recipesButton = createButton("Look for Christmas Recipes");
//        JButton songsButton = createButton("Play Christmas Songs");
//        JButton exchangeButton = createButton("Exchange Gifts");
//        JButton rpgButton = createButton("Start an adventure");

        // Add ActionListener to buttons
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
                RecipesFrame recipesFrame = new RecipesFrame(name);
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
                RPGFrame rpgFrame= new RPGFrame(name);
                rpgFrame.setVisible(true);
            }
        });

        // Set layout and add components
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
