import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DecorateFrame extends JFrame{

    private class Buttons extends JButton {
        int r;
        int c;

        public Buttons(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    int tileSize = 50;
    int numRows = 6;
    int numCols = numRows;
    int boardWidth = numCols * tileSize;
    int boardHeight = numRows * tileSize;

//    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    private JLabel imageLabel = new JLabel();
    private BufferedImage[] images ;
    private int currentImageIndex;

    Boolean gameOver = false;

    Buttons[][] board = new Buttons[numRows][numCols];
    ArrayList<Buttons> surprise;

    Random random = new Random();

    int moves = 0;
    int posR;
    int posC;
    JLabel movesLabel = new JLabel("Moves Left: 10");
    JButton restartButton = new JButton("Restart");
    JButton backButton = new JButton("Back to Menu Page");

    DecorateFrame(String name){
//        frame.setVisible(true);
        setSize(1122, 650);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

//        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
//        textLabel.setHorizontalAlignment(JLabel.CENTER);
//        textLabel.setText("testing lng ni ehe");
//        textLabel.setOpaque(true);

        imageLabel.setHorizontalAlignment(JLabel.CENTER);


        textPanel.setLayout(new BorderLayout());
//        textPanel.add(textLabel);
        textPanel.add(imageLabel);
        add(textPanel, BorderLayout.NORTH);

        try {
            BufferedImage img1 = ImageIO.read(new File("trees/tree1.png"));
            BufferedImage img2 = ImageIO.read(new File("trees/tree2.png"));
            BufferedImage img3 = ImageIO.read(new File("trees/tree3.png"));

            images = new BufferedImage[]{img1, img2, img3};
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

                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) { //pwede ramn unta ni actionlistener pero nanguha rakog code somewhere hehe

                        Buttons btn = (Buttons) e.getSource();
                        //left click
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (btn.getText() == "") {
                                btn.setEnabled(false);
                                if (surprise.contains(btn)) {
                                    currentImageIndex = (currentImageIndex + 1) % images.length;
                                    setImage(images[currentImageIndex]);
                                    btn.setText("  ❄️");
                                    JOptionPane.showMessageDialog(null, "You got the surprise decorator!");
                                    if (currentImageIndex != images.length-1) restartGame(); //mag restart ang game until sa last image then ambot unsay mahitabo after ana
                                    gameOver = true;
                                } else {
                                    hint(btn.r, btn.c, btn);
                                }
                            }
                        }
                        if (!gameOver) {
                            moves++;
                            if (moves > 10) { //after 10 moves kay balik
                                JOptionPane.showMessageDialog(null, "You've reached the maximum number of moves. Restarting the game.");
                                restartGame();
                            }
                        }
                    }
                });

                boardPanel.add(btn);
            }
        }

        setVisible(true); //sa frame ni

        //mu set kng asa mu lugar si snowflake (randomly)
        surprise = new ArrayList<Buttons>();

        posR = random.nextInt(numRows);
        posC = random.nextInt(numCols);
//
//        int r = random.nextInt(numRows);
//        int c = random.nextInt(numCols);

        Buttons btn = board[posR][posC];
        if (!surprise.contains(btn)){
            surprise.add(btn);
        }
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

    //hints given if duol naka sa snowflake
    void hint(int r, int c, Buttons btn){
        if ( (r == posR-1 && c == posC) ||
                (r == posR+1 && c == posC) ||
                (r == posR && c == posC-1) ||
                (r == posR && c == posC+1)) btn.setText("?");
    }

    //mu restart ang game if mahutdan shag moves
    private void restartGame() {
        moves = 0;

        updateMovesLabel();
        surprise.clear();
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                Buttons btn = board[r][c];
                btn.setText("");
                btn.setEnabled(true);
            }
        }
        posR = random.nextInt(numRows);
        posC = random.nextInt(numCols);

        Buttons btn = board[posR][posC];
        if (!surprise.contains(btn)){
            surprise.add(btn);
        }
    }
    private void updateMovesLabel() {
        int movesLeft = 10 - moves;
        movesLabel.setText("Moves Left: " + movesLeft);
    }

    //transition to another tree (basta mu appear sha mu change) after makita niya ang snowflake or unsa
    private void setImage(BufferedImage image) {
        ImageIcon icon = new ImageIcon(image);
        imageLabel.setIcon(icon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DecorateFrame decorateFrame = new DecorateFrame("Player");
            decorateFrame.setVisible(true);
        });
    }
}

//note:
// mu repeat until ma fully decorate na ang tree
// button for back to main menu
// if usa lng na round, then mag while (big note na daghan modification ani especially sa hint() )
// basta kana ang idea




//----------------------------------------------------------------------------------------
//end of the line

//// Component interface
//interface ChristmasTree {
//    void draw(Graphics g);
//}
//
//// Concrete component
//class BaseChristmasTree implements ChristmasTree {
//    @Override
//    public void draw(Graphics g) {
//        // Draw the base tree
//        g.setColor(new Color(139, 69, 19)); // Brown color
//        g.fillRect(450, 300, 20, 100); // Tree trunk
//        g.setColor(new Color(34, 139, 34)); // Green color
//        g.fillPolygon(new int[]{400, 500, 600}, new int[]{300, 100, 300}, 3); // Tree body
//    }
//}
//
//// Decorator interface
//abstract class TreeDecorator implements ChristmasTree {
//    protected ChristmasTree decoratedTree;
//
//    public TreeDecorator(ChristmasTree decoratedTree) {
//        this.decoratedTree = decoratedTree;
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        decoratedTree.draw(g);
//    }
//}
//
//// Concrete decorators
//class LightsDecorator extends TreeDecorator {
//    public LightsDecorator(ChristmasTree decoratedTree) {
//        super(decoratedTree);
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        super.draw(g);
//        // Add lights decoration
//        g.setColor(Color.YELLOW);
//        g.fillOval(450, 150, 20, 20); // Example: Lights on the tree
//    }
//}
//
//class StarDecorator extends TreeDecorator {
//    public StarDecorator(ChristmasTree decoratedTree) {
//        super(decoratedTree);
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        super.draw(g);
//        // Add star decoration
//        g.setColor(Color.YELLOW);
//        g.fillPolygon(new int[]{460, 470, 480}, new int[]{80, 40, 80}, 3); // Example: Star on top
//    }
//}
//
//class BallsDecorator extends TreeDecorator {
//    public BallsDecorator(ChristmasTree decoratedTree) {
//        super(decoratedTree);
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        super.draw(g);
//        // Add balls decoration
//        g.setColor(Color.RED);
//        g.fillOval(430, 180, 20, 20); // Example: Red ball
//        g.setColor(Color.BLUE);
//        g.fillOval(470, 180, 20, 20); // Example: Blue ball
//    }
//}
//
//class TinselDecorator extends TreeDecorator {
//    public TinselDecorator(ChristmasTree decoratedTree) {
//        super(decoratedTree);
//    }
//
//    @Override
//    public void draw(Graphics g) {
//        super.draw(g);
//        // Add tinsel decoration
//        g.setColor(Color.MAGENTA);
//        g.drawLine(450, 100, 450, 200); // Example: Magenta tinsel
//    }
//}
//
//public class DecorateFrame extends JFrame {
//
//    private ChristmasTree christmasTree;
//
//    public DecorateFrame(String name) {
//        setTitle("Decorate Christmas Tree");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(800, 600);
//        setResizable(false);
//        setLocationRelativeTo(null);
//
//        // Create the base Christmas tree
//        christmasTree = new BaseChristmasTree();
//
//        // Create and add components
//        JButton lightsButton = createDecorationButton("Add Lights");
//        JButton starButton = createDecorationButton("Add Star");
//        JButton ballsButton = createDecorationButton("Add Balls");
//        JButton tinselButton = createDecorationButton("Add Tinsel");
//
//        // Set layout and add components
//        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
//        add(lightsButton);
//        add(starButton);
//        add(ballsButton);
//        add(tinselButton);
//    }
//
//    private JButton createDecorationButton(String decoration) {
//        JButton button = new JButton(decoration);
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Decorate the tree based on the button clicked
//                switch (decoration) {
//                    case "Add Lights":
//                        christmasTree = new LightsDecorator(christmasTree);
//                        break;
//                    case "Add Star":
//                        christmasTree = new StarDecorator(christmasTree);
//                        break;
//                    case "Add Balls":
//                        christmasTree = new BallsDecorator(christmasTree);
//                        break;
//                    case "Add Tinsel":
//                        christmasTree = new TinselDecorator(christmasTree);
//                        break;
//                }
//
//                repaint(); // Trigger repaint to update the tree with decorations
//            }
//        });
//        return button;
//    }
//
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        // Draw the Christmas tree with decorations
//        christmasTree.draw(g);
//    }
//}