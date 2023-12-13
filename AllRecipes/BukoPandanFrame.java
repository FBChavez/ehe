package AllRecipes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import MainPackage.RecipeFrame;

public class BukoPandanFrame extends JFrame {

    private String playerName;
    private JPanel jPanel;
    private JTextPane descriptionTP;
    private JScrollPane descriptionSP;
    private JList ingredientsList;
    private JScrollPane ingredientsSP;
    private JList instructionsList;
    private JScrollPane instructionsSP;
    private JScrollPane notesSP;
    private JList notesList;
    private JButton backToRecipePageButton;
    private JLabel recipenameLabel;
    private JLabel foodImg;

    public BukoPandanFrame(String name) {
        this.playerName = name;


        setTitle("Christmas Recipes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 675);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(jPanel);
        backToRecipePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RecipeFrame recipeFrame = new RecipeFrame(name);
                recipeFrame.setVisible(true);
            }
        });
    }

}
