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

    private int currentImageIndex;
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

        currentImageIndex = 0;
        setImage(images[currentImageIndex]); //para saa images

        boardPanel.setLayout(new GridLayout(numRows, numCols));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200)); //murag ni sha margin sa boardPanel
        add(boardPanel);

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                Buttons btn = new Buttons(r, c);
                board[r][c] = btn;

                btn.setFocusable(false);
                btn.setMargin(new Insets(0,0,0,0));
                btn.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30)); //para ni sa mga question mark (hint)
                //pwede ra tangtangon si font if mag sa pag hatag hints/sa snowflake

                clicking(btn);

                boardPanel.add(btn);
            }
        }

        setVisible(true); //sa frame ni

        setSnowflake();

        // Add buttons and moves label
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(movesLabel);
        buttonPanel.add(restartButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for the restart button
        restartButton.addActionListener(e -> restartGame());

        // Action listener for the back button (change this to go back to name page logic)
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuFrame menuFrame = new MenuFrame(name);
                menuFrame.setVisible(true);
            }
        });

    }

    void clicking(Buttons btn){
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { //pwede ramn unta ni actionlistener pero nanguha rakog code somewhere hehe

                Buttons btn = (Buttons) e.getSource();
                //left click
                if (e.getButton() == MouseEvent.BUTTON1) {
                    updateMovesLabel();

                    if (btn.getText() == "") {
                        btn.setEnabled(false);

                        if (surprise.contains(btn)) {
                            currentImageIndex = (currentImageIndex + 1) % images.length;
                            setImage(images[currentImageIndex]);

                            ButtonDecorate snowflake = new HiddenSnowflake(btn);
                            btn.setDecorate(snowflake);
                            btn.hiddenSnowflake();

                            if (currentImageIndex != images.length-1) {
                                JOptionPane.showMessageDialog(null, "You got the surprise decorator!");
                                restartGame(); //mag restart ang game until sa last image then ambot unsay mahitabo after ana
                            } else { //balik sa menu if fully decorerated na ang tree
                                JOptionPane.showMessageDialog(null, "Well done, " + name + "! You have fully decorated the Christmas Tree!");
                                MenuFrame menu = new MenuFrame(name);
                                setVisible(false);
                                menu.setVisible(true);
                            }
                            gameOver = true;
                        } else {
                            hint(btn.r, btn.c, btn);
                        }
                    }
                }
                if (!gameOver) {
                    if (moves < 1) { //after 10 moves kay balik
                        JOptionPane.showMessageDialog(null, "You've reached the maximum number of moves. Restarting the game.");
                        restartGame();
                    }
                }
            }
        });
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

    //mu restart ang game if mahutdan shag moves
    private void restartGame() {
        surprise.clear();

        moves = 10; // Reset the moves to the initial value
        updateMovesLabel();

        // Reset the tree and set a new surprise snowflake
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                Buttons btn = board[r][c];
                btn.setText("");
                btn.setEnabled(true);
                clicking(btn);
            }
        }
        setSnowflake();
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

//note:
// mu repeat until ma fully decorate na ang tree
// button for back to main menu
// if usa lng na round, then mag while (big note na daghan modification ani especially sa hint() )
// basta kana ang idea
