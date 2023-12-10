import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Component interface
interface ChristmasTree {
    void draw(Graphics g);
}

// Concrete component
class BaseChristmasTree implements ChristmasTree {
    @Override
    public void draw(Graphics g) {
        // Draw the base tree
        g.setColor(new Color(139, 69, 19)); // Brown color
        g.fillRect(450, 300, 20, 100); // Tree trunk
        g.setColor(new Color(34, 139, 34)); // Green color
        g.fillPolygon(new int[]{400, 500, 600}, new int[]{300, 100, 300}, 3); // Tree body
    }
}

// Decorator interface
abstract class TreeDecorator implements ChristmasTree {
    protected ChristmasTree decoratedTree;

    public TreeDecorator(ChristmasTree decoratedTree) {
        this.decoratedTree = decoratedTree;
    }

    @Override
    public void draw(Graphics g) {
        decoratedTree.draw(g);
    }
}

// Concrete decorators
class LightsDecorator extends TreeDecorator {
    public LightsDecorator(ChristmasTree decoratedTree) {
        super(decoratedTree);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        // Add lights decoration
        g.setColor(Color.YELLOW);
        g.fillOval(450, 150, 20, 20); // Example: Lights on the tree
    }
}

class StarDecorator extends TreeDecorator {
    public StarDecorator(ChristmasTree decoratedTree) {
        super(decoratedTree);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        // Add star decoration
        g.setColor(Color.YELLOW);
        g.fillPolygon(new int[]{460, 470, 480}, new int[]{80, 40, 80}, 3); // Example: Star on top
    }
}

class BallsDecorator extends TreeDecorator {
    public BallsDecorator(ChristmasTree decoratedTree) {
        super(decoratedTree);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        // Add balls decoration
        g.setColor(Color.RED);
        g.fillOval(430, 180, 20, 20); // Example: Red ball
        g.setColor(Color.BLUE);
        g.fillOval(470, 180, 20, 20); // Example: Blue ball
    }
}

class TinselDecorator extends TreeDecorator {
    public TinselDecorator(ChristmasTree decoratedTree) {
        super(decoratedTree);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        // Add tinsel decoration
        g.setColor(Color.MAGENTA);
        g.drawLine(450, 100, 450, 200); // Example: Magenta tinsel
    }
}

public class DecorateFrame extends JFrame {

    private ChristmasTree christmasTree;

    public DecorateFrame(String name) {
        setTitle("Decorate Christmas Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        // Create the base Christmas tree
        christmasTree = new BaseChristmasTree();

        // Create and add components
        JButton lightsButton = createDecorationButton("Add Lights");
        JButton starButton = createDecorationButton("Add Star");
        JButton ballsButton = createDecorationButton("Add Balls");
        JButton tinselButton = createDecorationButton("Add Tinsel");

        // Set layout and add components
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        add(lightsButton);
        add(starButton);
        add(ballsButton);
        add(tinselButton);
    }

    private JButton createDecorationButton(String decoration) {
        JButton button = new JButton(decoration);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Decorate the tree based on the button clicked
                switch (decoration) {
                    case "Add Lights":
                        christmasTree = new LightsDecorator(christmasTree);
                        break;
                    case "Add Star":
                        christmasTree = new StarDecorator(christmasTree);
                        break;
                    case "Add Balls":
                        christmasTree = new BallsDecorator(christmasTree);
                        break;
                    case "Add Tinsel":
                        christmasTree = new TinselDecorator(christmasTree);
                        break;
                }

                repaint(); // Trigger repaint to update the tree with decorations
            }
        });
        return button;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Draw the Christmas tree with decorations
        christmasTree.draw(g);
    }
}