package MainPackage;

import MainPackage.RecipeMenuFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PastaCarbonaraFrame extends JFrame {

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

    public PastaCarbonaraFrame(String name) {
        this.playerName = name;


        setTitle("Christmas Recipes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(660, 730);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(jPanel);
        backToRecipePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RecipeMenuFrame recipeMenuFrame = new RecipeMenuFrame(name);
                recipeMenuFrame.setVisible(true);
            }
        });
    }

}
