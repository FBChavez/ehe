import javax.swing.*;

public class RecipesFrame extends JFrame {

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
    private JLabel recipenameLabel;

    public RecipesFrame(String name) {
        this.playerName = name;

        setTitle("Christmas Recipes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(jPanel);
    }
}
