import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ExchangeGiftsFrame extends JFrame {
    private ArrayList<String> lists;
    public ExchangeGiftsFrame(String name) {
        setTitle("Merry Christmas Greeting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1122, 793);
        setResizable(false);
        setLocationRelativeTo(null);

        String lbl = "<html>" +
                "<center>Hey there, " + name + "!<br> Are you excited to know who your manito/manita is?</center>" +
                "</html>";

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel(lbl);
        JButton tofindManits = new JButton("SO EXCITED!");

        tofindManits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String manito = findManits(name);
                if (manito != null) JOptionPane.showMessageDialog(null, "<html>Your manito/manita is <br>" + manito + "!</html>");
            }
        });

        lists = getManits(name);

        label.setHorizontalAlignment(JLabel.CENTER);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        panel.add(label, constraints);

        constraints.gridy = 1;
        panel.add(tofindManits, constraints);

        add(panel);
    }

    private ArrayList<String> getManits(String name){
        ArrayList<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("names.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    private String findManits(String name){
        if (lists.size()-1 == 0) {
            JOptionPane.showMessageDialog(null, "It's just you and me ;.;");
            return null;
        } else {
            Random random = new Random();
            int randomIndex;

            do {
                randomIndex = random.nextInt(lists.size());
            } while (lists.get(randomIndex).equals(name));

            return lists.get(randomIndex);
        }
    }
}
