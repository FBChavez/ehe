import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SongsFrame extends JFrame {

    private JList<String> songsList;
    private DefaultListModel<String> listModel;
    private Clip clip;

    public SongsFrame(String name) {
        setTitle("Christmas Songs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Sample list of Christmas songs
        String[] songs = {"Jingle Bells", "Silent Night", "Deck the Halls", "Joy to the World"};

        // Create a DefaultListModel for the JList
        listModel = new DefaultListModel<>();
        for (String song : songs) {
            listModel.addElement(song);
        }

        // Create JList with the DefaultListModel
        songsList = new JList<>(listModel);
        songsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create Play and Pause buttons
        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");
        JButton backButton = new JButton("Back");
        pauseButton.setEnabled(false); // Initially, pause button is disabled

        // Add ActionListener to Play button
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSelectedSong();
                playButton.setEnabled(false);
                pauseButton.setEnabled(true);
            }
        });

        // Add ActionListener to Pause button
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseSelectedSong();
                playButton.setEnabled(true);
                pauseButton.setEnabled(false);
            }
        });

        // Add ListSelectionListener to handle song selection
        songsList.addListSelectionListener(e -> {
            pauseSelectedSong(); // Pause the current song when a new song is selected
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuFrame menuFrame = new MenuFrame(name);
                menuFrame.setVisible(true);
            }
        });
        // Set layout and add components
        setLayout(new BorderLayout());
        add(new JScrollPane(songsList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void playSelectedSong() {
        String selectedSong = songsList.getSelectedValue();
        if (selectedSong != null) {
            try {
                // Replace "path/to/songs/" with the actual path to your songs
                String songPath = "path/to/songs/" + selectedSong + ".wav";
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(songPath));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void pauseSelectedSong() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}