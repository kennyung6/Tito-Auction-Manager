package ui;

import helpers.FancyProgressBar;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class SplashFrame extends JFrame{

    JLabel imgLabel;
    Container container;

    // class constants
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 250;

    public void createAndDisplayGUI()
    {
        setTitle("Welcome");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        // Remove JFrame Toolbar
        setUndecorated(true);
        container = getContentPane();
        container.setBackground(new Color(249,249,252));

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder());
        container.add(panel, BorderLayout.CENTER);

        // Image setting
        String path = "/images/titusplash.png";
        ImageIcon icon = new ImageIcon(SplashFrame.class.getResource(path));
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(500, 350,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        imgLabel = new JLabel(icon);
        imgLabel.setBackground(Color.white);
        container.add(imgLabel, BorderLayout.CENTER);


        // Init Progress Bar
        JProgressBar fancyPB = new JProgressBar();
        fancyPB.setBackground(Color.WHITE);
        fancyPB.setUI(new FancyProgressBar());
        container.add(fancyPB, BorderLayout.SOUTH);


        // additional settings for window frame.
        Dimension d = new Dimension(500,400);
        container.setPreferredSize(d);
        pack();

        // this method display the JFrame to center position of a screen
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);


        // init Timer for Progress Bar
        Timer timer = new Timer(100, new ActionListener() {

            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                fancyPB.setValue(count);
                count++;
                if (count >= 100) {
                    ((Timer)e.getSource()).stop();
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                    dispose();
                }
            }
        });
        timer.start();
    }

}