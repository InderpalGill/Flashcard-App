package ui;

import javax.swing.*;
import java.awt.*;

//Creates a SplashScreen that will display an image for 2 seconds when application starts then closes.
public class SplashScreen extends JFrame {

    //EFFECTS: Constructor for class. Takes an ImageIcon
    public SplashScreen(ImageIcon imageIcon) {
        JLabel label = new JLabel(imageIcon);
        getContentPane().add(label, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // Center the splash screen on the screen
        setVisible(true);

        // Simulate some loading time
        try {
            Thread.sleep(2000); // 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dispose();
    }
}
