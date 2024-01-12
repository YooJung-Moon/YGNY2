package YGNY;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KioskFrame frame = new KioskFrame();
            frame.setVisible(true);
        });
    }
}
