import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SongsFrame extends JFrame {

    private JList<String> songsList;
    private DefaultListModel<String> listModel;
    private Clip clip;
    private long clipTimePosition = 0;
    private JProgressBar progressBar;
    private boolean isDragging = false;

    public SongsFrame(String name) {
        setTitle("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel progressPanel = new JPanel(new BorderLayout());

        // Sample list of songs
        String[] songs = {"All I Want For Christmas", "Jingle Bell Rock", "Silent Night", "Deck the Halls", "Joy to the World"};

        listModel = new DefaultListModel<>();
        for (String song : songs) {
            listModel.addElement(song);
        }

        songsList = new JList<>(listModel);
        songsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");
        JButton backButton = new JButton("Back");
        JButton shuffleButton = new JButton("Shuffle");

        playButton.setPreferredSize(new Dimension(80, 30));
        pauseButton.setPreferredSize(new Dimension(80, 30));
        backButton.setPreferredSize(new Dimension(80, 30));
        shuffleButton.setPreferredSize(new Dimension(80, 30));

        pauseButton.setEnabled(false);

        playButton.addActionListener(e -> {
            playSelectedSong();
            playButton.setEnabled(false);
            pauseButton.setEnabled(true);
        });

        pauseButton.addActionListener(e -> {
            pauseSelectedSong();
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
        });

        songsList.addListSelectionListener(e -> {
            pauseSelectedSong();
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
            progressBar.setValue(0); // Reset progress bar when a new song is selected
        });

        backButton.addActionListener(e -> {
            stopSong();
            dispose();
            MenuFrame menuFrame = new MenuFrame(name);
            menuFrame.setVisible(true);
        });

        shuffleButton.addActionListener(e -> {
            stopSong();
            progressBar.setValue(0); // Reset progress bar for shuffle
            playRandomSong();
        });

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        progressBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                double width = progressBar.getWidth();
                double newPercentage = (mouseX / width) * 100;
                progressBar.setValue((int) newPercentage);
                clipTimePosition = (long) ((newPercentage / 100.0) * clip.getMicrosecondLength());
                clip.setMicrosecondPosition(clipTimePosition);
                isDragging = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDragging) {
                    int mouseX = e.getX();
                    double width = progressBar.getWidth();
                    double newPercentage = (mouseX / width) * 100;
                    progressBar.setValue((int) newPercentage);
                    clipTimePosition = (long) ((newPercentage / 100.0) * clip.getMicrosecondLength());
                    clip.setMicrosecondPosition(clipTimePosition);
                    isDragging = false;
                }
            }
        });

        progressBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    int mouseX = e.getX();
                    double width = progressBar.getWidth();
                    double newPercentage = (mouseX / width) * 100;
                    progressBar.setValue((int) newPercentage);
                    clipTimePosition = (long) ((newPercentage / 100.0) * clip.getMicrosecondLength());
                    clip.setMicrosecondPosition(clipTimePosition);
                }
            }
        });

        controlPanel.add(backButton);
        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(shuffleButton);

        progressPanel.add(progressBar, BorderLayout.CENTER);

        contentPanel.add(new JScrollPane(songsList), BorderLayout.CENTER);
        contentPanel.add(progressPanel, BorderLayout.SOUTH);
        contentPanel.add(controlPanel, BorderLayout.NORTH);

        getContentPane().add(contentPanel);
    }

    private void playSelectedSong() {
        String selectedSong = songsList.getSelectedValue();
        if (selectedSong != null) {
            try {
                String songPath = "songs/" + selectedSong + ".wav";
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(songPath));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                if (clipTimePosition > 0) {
                    clip.setMicrosecondPosition(clipTimePosition);
                }

                clip.start();

                Thread updateProgressBarThread = new Thread(() -> {
                    while (clip.isRunning()) {
                        try {
                            progressBar.setValue((int) ((clip.getMicrosecondPosition() * 100) / clip.getMicrosecondLength()));
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                updateProgressBarThread.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void pauseSelectedSong() {
        if (clip != null && clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    private void stopSong() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    private void playRandomSong() {
        List<String> songList = Collections.list(listModel.elements());
        Collections.shuffle(songList);
        String randomSong = songList.get(new Random().nextInt(songList.size()));
        songsList.setSelectedValue(randomSong, true);
        playSelectedSong();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SongsFrame frame = new SongsFrame("Songs");
            frame.setVisible(true);
        });
    }
}
