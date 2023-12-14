package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ExchangeGiftsFrame extends JFrame {
    private ArrayList<String> lists;

    public ExchangeGiftsFrame(String name) {
        setTitle("Merry Christmas Greeting");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(612, 416);
        setResizable(false);
        setLocationRelativeTo(null);

        String lbl = "<html>" +
                "<center>Hey there, " + name + "!<br> Are you excited to know who your sugar daddy/mommy is sugar baby?</center>" +
                "</html>";

        ImageIcon gifIcon = new ImageIcon("Photos/manito-manita.jpg");
        JLabel gifLabel = new JLabel(gifIcon);

        // Set the content pane to the JLabel
        setContentPane(gifLabel);

        // Create and add components
        JLabel label = new JLabel(lbl);

        JButton tofindManits = new JButton("SO EXCITED!");

        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuFrame menuFrame = new MenuFrame(name);
                menuFrame.setVisible(true);
            }
        });

        tofindManits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String manito = findManits(name);
                if (manito != null) JOptionPane.showMessageDialog(null, "<html>Your manito/manita is <br>" + manito + "!</html>");
            }
        });
        lists = getManits(name);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(tofindManits, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        panel.add(backButton, gbc);

        // Set the content pane to the JPanel
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
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
