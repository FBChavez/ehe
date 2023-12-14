package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipeMenuFrame extends JFrame {

    public RecipeMenuFrame(String name) {
        setTitle("All Recipes Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1122, 793);
        setResizable(false);
        setLocationRelativeTo(null);

        // Set background color
//        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color
        ImageIcon gifIcon = new ImageIcon("kitchen2.gif");
        JLabel gifLabel = new JLabel(gifIcon);

        // Set the content pane to the JLabel
        setContentPane(gifLabel);
        // Create a JLabel for the title
        JLabel titleLabel = new JLabel(" ");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create buttons with equal size

//        JButton decorateButton = createButton("Decorate Virtual Christmas Tree");
//        JButton recipesButton = createButton("Look for Christmas Recipes");
//        JButton songsButton = createButton("Play Christmas Songs");
//        JButton exchangeButton = createButton("Exchange Gifts");
//        JButton rpgButton = createButton("Start an adventure");



        JButton bukopandanBtn = createButton();
        ImageIcon bukopanBtn = new ImageIcon("iconBTN/buko.png");
        Image imageD1 = bukopanBtn.getImage();
        Image imageD2 = imageD1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        bukopandanBtn.setIcon(new ImageIcon(imageD2));

        JButton lecheflanBtn = createButton();
        ImageIcon lecheBtn = new ImageIcon("iconBTN/leche.png");
        Image imageR1 = lecheBtn.getImage();
        Image imageR2 = imageR1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        lecheflanBtn.setIcon(new ImageIcon(imageR2));

        JButton lechonkawaliBtn = createButton();
        ImageIcon lechonBtn = new ImageIcon("iconBTN/lechon.png");
        Image imageS1 = lechonBtn.getImage();
        Image imageS2 = imageS1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        lechonkawaliBtn.setIcon(new ImageIcon(imageS2));

        JButton lumpiaBtn = createButton();
        ImageIcon lumpsBtn = new ImageIcon("iconBTN/lumpia.png");
        Image imageE1 = lumpsBtn.getImage();
        Image imageE2 = imageE1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        lumpiaBtn.setIcon(new ImageIcon(imageE2));

        JButton pastaBtn = createButton();
        ImageIcon carbsBtn = new ImageIcon("iconBTN/pasta.png");
        Image imageG1 = carbsBtn.getImage();
        Image imageG2 = imageG1.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        pastaBtn.setIcon(new ImageIcon(imageG2));

        JButton backButton = new JButton("Back to Main Menu");
        backButton.setPreferredSize(new Dimension(300, 50));
        bukopandanBtn.setBackground(new Color(173, 216, 230)); // Light blue color
        lecheflanBtn.setBackground(new Color(255, 192, 203)); // Pink color
        lechonkawaliBtn.setBackground(new Color(144, 238, 144)); // Light green color
        lumpiaBtn.setBackground(new Color(255, 215, 0)); // Gold color
        pastaBtn.setBackground(new Color(255, 182, 193)); // Light pink color
        backButton.setBackground(new Color(255, 99, 71)); // Tomato red color

        // Add ActionListener to buttons
        bukopandanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                BukoPandanFrame bukoPandanFrame = new BukoPandanFrame(name);
                bukoPandanFrame.setVisible(true);
//                decorateFrame.setVisible(true);
            }
        });

        lecheflanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LecheFlanFrame lecheFlanFrame = new LecheFlanFrame(name);
                lecheFlanFrame.setVisible(true);

            }
        });

        lechonkawaliBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LechonKawaliFrame lechonKawaliFrame = new LechonKawaliFrame(name);
                lechonKawaliFrame.setVisible(true);
            }
        });

        lumpiaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LumpiaFrame lumpiaFrame = new LumpiaFrame(name);
                lumpiaFrame.setVisible(true);
            }
        });

        pastaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                PastaCarbonaraFrame pastaCarbonaraFrame= new PastaCarbonaraFrame(name);
                pastaCarbonaraFrame.setVisible(true);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuFrame menuFrame = new MenuFrame(name);
                menuFrame.setVisible(true);
            }
        });

        // Set layout and add components
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(100, 0, 20, 0);
        add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(8, 0, 10, 0);
        add(bukopandanBtn, gbc);

        gbc.gridy = 2;
        add(lecheflanBtn, gbc);

        gbc.gridy = 3;
        add(lechonkawaliBtn, gbc);

        gbc.gridy = 4;
        add(lumpiaBtn, gbc);

        gbc.gridy = 5;
        add(pastaBtn, gbc);

        gbc.gridy = 6;
        add(backButton, gbc);
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(300, 50));
        return button;
    }
}
