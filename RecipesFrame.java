import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipesFrame extends JFrame {

    private String playerName;

    public RecipesFrame(String name) {
        this.playerName = name;

        setTitle("Christmas Recipes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);

        // Set background color
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color

        // Create a JLabel for the title
        JLabel titleLabel = new JLabel("Here are some recipes that you may like, " + playerName);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create buttons for recipes
        JButton turkeyButton = createRecipeButton("Roasted Turkey");
        JButton cookiesButton = createRecipeButton("Christmas Cookies");
        JButton eggnogButton = createRecipeButton("Eggnog");

        // Create "Back to Main Menu" button
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuFrame menuFrame = new MenuFrame(playerName);
                menuFrame.setVisible(true);
            }
        });

        // Set layout and add components
        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);

        JPanel recipesPanel = new JPanel(new GridLayout(3, 1, 20, 20));
        recipesPanel.setOpaque(false);
        recipesPanel.add(turkeyButton);
        recipesPanel.add(cookiesButton);
        recipesPanel.add(eggnogButton);
        add(recipesPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JButton createRecipeButton(String recipeName) {
        JButton button = new JButton(recipeName);
        button.setFont(new Font("Arial", Font.BOLD, 16)); // Set font size
        button.setPreferredSize(new Dimension(300, 50)); // Set preferred size
        return button;
    }
}
