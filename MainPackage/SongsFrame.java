package MainPackage;

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
    private boolean isShufflePlaying = false;

    public SongsFrame(String name) {
        setTitle("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 250);
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
        songsList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                renderer.setHorizontalAlignment(SwingConstants.CENTER); // Align text to the center
                return renderer;
            }
        });
        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");
        JButton backButton = new JButton("Back");
        JButton shuffleButton = new JButton("Random");

        playButton.setPreferredSize(new Dimension(80, 30));
        pauseButton.setPreferredSize(new Dimension(80, 30));
        backButton.setPreferredSize(new Dimension(80, 30));
        shuffleButton.setPreferredSize(new Dimension(80, 30));

        playButton.setBackground(Color.decode("#85171D"));
        pauseButton.setBackground(Color.decode("#343F37"));
        backButton.setBackground(Color.decode("#c8ae9a"));
        shuffleButton.setBackground(Color.decode("#581845"));

        playButton.setForeground(Color.decode("#FBF4E9"));
        pauseButton.setForeground(Color.decode("#FBF4E9"));
        backButton.setForeground(Color.decode("#FBF4E9"));
        shuffleButton.setForeground(Color.decode("#FBF4E9"));

        pauseButton.setEnabled(false);

        playButton.addActionListener(e -> {
            stopSong();
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
            progressBar.setValue(0);
        });

        backButton.addActionListener(e -> {
            stopSong();
            dispose();
            MenuFrame menuFrame = new MenuFrame(name);
            menuFrame.setVisible(true);
        });

        shuffleButton.addActionListener(e -> {
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
            stopSong();

            progressBar.setValue(0);
            playRandomSong();

        });

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setPreferredSize(new Dimension(progressBar.getPreferredSize().width, 50));
        progressBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleProgressBarMousePress(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleProgressBarMouseRelease(e);
            }
        });

        progressBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleProgressBarMouseDrag(e);
            }
        });

        controlPanel.add(backButton);
        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(shuffleButton);

        progressPanel.add(progressBar, BorderLayout.CENTER);

        JPanel songsListPanel = new JPanel(new BorderLayout());
        songsListPanel.setPreferredSize(new Dimension(400, 130));
        songsListPanel.setBorder(BorderFactory.createTitledBorder("Christmas Songs List"));

        JScrollPane songsScrollPane = new JScrollPane(songsList);
        songsListPanel.add(songsScrollPane, BorderLayout.CENTER);

        contentPanel.add(songsListPanel, BorderLayout.NORTH);
        contentPanel.add(progressPanel, BorderLayout.CENTER);
        contentPanel.add(controlPanel, BorderLayout.SOUTH);

        getContentPane().add(contentPanel);
    }

    private void handleProgressBarMousePress(MouseEvent e) {
        if (clip != null) {
            int mouseX = e.getX();
            double width = progressBar.getWidth();
            double newPercentage = (mouseX / width) * 100;
            progressBar.setValue((int) newPercentage);
            if (clip.getMicrosecondLength() != 0) {
                clipTimePosition = (long) ((newPercentage / 100.0) * clip.getMicrosecondLength());
                clip.setMicrosecondPosition(clipTimePosition);
            }
            isDragging = true;
        }
    }

    private void handleProgressBarMouseRelease(MouseEvent e) {
        if (isDragging && clip != null) {
            int mouseX = e.getX();
            double width = progressBar.getWidth();
            double newPercentage = (mouseX / width) * 100;
            progressBar.setValue((int) newPercentage);
            if (clip.getMicrosecondLength() != 0) {
                clipTimePosition = (long) ((newPercentage / 100.0) * clip.getMicrosecondLength());
                clip.setMicrosecondPosition(clipTimePosition);
            }
            isDragging = false;
        }
    }

    private void handleProgressBarMouseDrag(MouseEvent e) {
        if (isDragging) {
            int mouseX = e.getX();
            double width = progressBar.getWidth();
            double newPercentage = (mouseX / width) * 100;
            progressBar.setValue((int) newPercentage);
            clipTimePosition = (long) ((newPercentage / 100.0) * clip.getMicrosecondLength());
            clip.setMicrosecondPosition(clipTimePosition);
        }
    }

    private void playRandomSong() {
        List<String> songList = Collections.list(listModel.elements());
        Collections.shuffle(songList);
        String randomSong = songList.get(new Random().nextInt(songList.size()));

        clipTimePosition = 0;

        songsList.setSelectedValue(randomSong, true);
        playSelectedSong();
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SongsFrame frame = new SongsFrame("Songs");
            frame.setVisible(true);
        });
    }
}
