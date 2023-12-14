package MainPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


// Concrete Interface
interface ButtonDecorate {
    void hiddenSnowflake();
}

public class DecorateFrame extends JFrame{

    // Decorator
    private class HiddenSnowflake implements ButtonDecorate{
        private final Buttons b;

        public HiddenSnowflake(Buttons b){
            this.b = b;
        }

        @Override
        public void hiddenSnowflake() {
            b.setText("  ❄️");
        }
    }

    // Concrete Component
    private class Buttons extends JButton {
        private ButtonDecorate decorate;
        int r;
        int c;

        public Buttons(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void setDecorate(ButtonDecorate decorate){
            this.decorate = decorate;
        }

        public void hiddenSnowflake(){
            if (decorate != null){
                decorate.hiddenSnowflake();
            }
        }
    }

    private int currentImageIndex = 0;
    int numRows = 6;
    int numCols = numRows;
    private int moves = 10;
    private int posR;
    private int posC;
    boolean gameOver = false;
    Buttons[][] board = new Buttons[numRows][numCols];
    ArrayList<Buttons> surprise;
    Random random = new Random();
    JLabel movesLabel = new JLabel("Moves Left: " + moves);
    JButton restartButton = new JButton("Restart");
    JButton backButton = new JButton("Back to Menu Page");
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    private JLabel imageLabel = new JLabel();
    private BufferedImage[] images ;
    private String name;

    private void showGameMechanicsInfo() {
        String message = "Look for the hidden snowflake to decorate the Christmas Tree. "
                + "There are clues surrounding the hidden snowflake. Good Luck and Enjoy!";
        JOptionPane.showMessageDialog(this, message, "Game Mechanics", JOptionPane.INFORMATION_MESSAGE);
    }

    DecorateFrame(String name){
        this.name = name;

        setSize(1122, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(imageLabel);
        add(textPanel, BorderLayout.NORTH);

        try {
            BufferedImage img1 = ImageIO.read(new File("trees/tree1.png"));
            BufferedImage img2 = ImageIO.read(new File("trees/tree2.png"));
            BufferedImage img3 = ImageIO.read(new File("trees/tree3.png"));
            BufferedImage img4 = ImageIO.read(new File("trees/tree4.png"));
            BufferedImage img5 = ImageIO.read(new File("trees/tree5.png"));
            BufferedImage img6 = ImageIO.read(new File("trees/tree6.png"));
            BufferedImage img7 = ImageIO.read(new File("trees/tree7.png"));
            BufferedImage img8 = ImageIO.read(new File("trees/tree8.png"));

            images = new BufferedImage[]{img1, img4, img6, img7, img2, img5, img3, img8};

        } catch (IOException e) {
            e.printStackTrace();
        }

//        currentImageIndex = 0;
//        setImage(images[currentImageIndex]); //para saa images
//
//        boardPanel.setLayout(new GridLayout(numRows, numCols));
//        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200)); //murag ni sha margin sa boardPanel
        add(boardPanel);
        // Add buttons and moves label
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(movesLabel);
        buttonPanel.add(restartButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        initializeGame();
        setButtonListeners();

        ImageIcon backgroundImage = new ImageIcon("background/decorateFrameBG.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        textPanel.setOpaque(false);
        boardPanel.setOpaque(false);
        buttonPanel.setOpaque(false);

        backgroundLabel.add(textPanel, BorderLayout.NORTH);
        backgroundLabel.add(boardPanel, BorderLayout.CENTER);
        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);

        restartButton.setBackground(Color.decode("#1E8449")); // Set a color for the restart button using a hexadecimal code
        restartButton.setForeground(Color.WHITE); // Set text color for restart button
        backButton.setBackground(Color.decode("#D35400")); // Set a color for the back button using a hexadecimal code
        backButton.setForeground(Color.WHITE);

        getContentPane().add(backgroundLabel);

        setVisible(true);

        showGameMechanicsInfo();
    }

//    currentImageIndex = 0;
    private void initializeGame() {

        setImage(images[currentImageIndex]);

        boardPanel.setLayout(new GridLayout(numRows, numCols));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));

        // Initialize the game board
        initializeBoard();
    }

    private void initializeBoard() {

        surprise = new ArrayList<>();
        moves = 11;
        updateMovesLabel();

        boardPanel.removeAll();

        // Initialize the game board buttons
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                Buttons btn = new Buttons(r, c);
                board[r][c] = btn;

                btn.setFocusable(false);
                btn.setMargin(new Insets(0, 0, 0, 0));
                btn.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));

                boardPanel.add(btn);
            }
        }

        setSnowflake();
    }

    private void setButtonListeners() {
        restartButton.addActionListener(e -> restartGame());
        backButton.addActionListener(e -> returnToMenu());

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Buttons btn = board[i][j];
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (btn.isEnabled())updateMovesLabel();
                        btn.setEnabled(false);
                        handleButtonClick(btn);
                    }
                });
            }
        }
    }

    private void handleButtonClick(Buttons btn) {
        if (!gameOver && moves > 0) {

            if (surprise.contains(btn)) {
                handleSurpriseButtonClick(btn);
            } else {
                hint(btn.r, btn.c, btn);
            }
        } else if (moves <= 0) {
            JOptionPane.showMessageDialog(null, "You've reached the maximum number of moves. Restarting the game.");
            restartGame();
        }
    }

    private void handleSurpriseButtonClick(Buttons btn) {
        currentImageIndex = (currentImageIndex + 1) % images.length;
        setImage(images[currentImageIndex]);

        ButtonDecorate snowflake = new HiddenSnowflake(btn);
        btn.setDecorate(snowflake);
        btn.hiddenSnowflake();

        if (currentImageIndex != images.length - 1) {
            JOptionPane.showMessageDialog(null, "You found a hidden ornament!");
            restartGame();
        } else {
            JOptionPane.showMessageDialog(null, "Well done, " + name + "! You have fully decorated the Christmas Tree!");
            returnToMenu();
        }
        gameOver = true;
    }

    private void restartGame() {
        gameOver = false;
        initializeBoard();
        setButtonListeners();
    }

    private void returnToMenu() {
        dispose();
        MenuFrame menuFrame = new MenuFrame(name);
        menuFrame.setVisible(true);
    }

    void setSnowflake(){
        //mu set kng asa mu lugar si snowflake (randomly)
        surprise = new ArrayList<Buttons>();

        posR = random.nextInt(numRows);
        posC = random.nextInt(numCols);

        Buttons btn = board[posR][posC];
        if (!surprise.contains(btn)){
            surprise.add(btn);
        }
    }
    //hints given if duol naka sa snowflake
    void hint(int r, int c, Buttons btn){
        if ( (r == posR-1 && c == posC) ||
                (r == posR+1 && c == posC) ||
                (r == posR && c == posC-1) ||
                (r == posR && c == posC+1)) btn.setText("?");
    }


    private void updateMovesLabel() {
        moves = moves - 1;
        movesLabel.setText("Moves Left: " + moves);
    }

    //transition to another tree (basta mu appear sha mu change) after makita niya ang snowflake or unsa
    private void setImage(BufferedImage image) {
        ImageIcon icon = new ImageIcon(image);
        imageLabel.setIcon(icon);
    }
}