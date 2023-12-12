import javax.swing.*;

public class RecipeFrame extends JFrame {

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

    public RecipeFrame(String name) {
        this.playerName = name;

        setTitle("Christmas Recipes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 675);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(jPanel);
    }
}
