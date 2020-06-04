import ui.SplashFrame;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SplashFrame().createAndDisplayGUI());
    }
}
