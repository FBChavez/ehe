package MainPackage;

import static javax.swing.SwingUtilities.*;

public class Main {
    public static void main(String[] args) {
        invokeLater(() -> {
            WelcomeFrame app = new WelcomeFrame();
            app.setVisible(true);
        });
    }
}
