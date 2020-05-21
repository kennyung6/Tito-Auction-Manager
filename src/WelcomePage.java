import util.FancyProgressBar;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class WelcomePage extends JFrame{

    private static final long serialVersionUID = 1L;


    JLabel imgLabel;


    Container container;

    // class constants
    private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 250;


    public static void main(String... args)
    {
        /*
         * Here we are Scheduling a JOB for Event Dispatcher
         * Thread. The code which is responsible for creating
         * and displaying our GUI or call to the method which
         * is responsible for creating and displaying your GUI
         * goes into this SwingUtilities.invokeLater(...) thingy.
         */
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }

                new WelcomePage().createAndDisplayGUI();
            }
        });
    }

    public void createAndDisplayGUI()
    {
        setTitle("Welcome");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        container = getContentPane();

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder());
        container.add(panel, BorderLayout.CENTER);

        // Image setting
        String path = "/resources/titusplash.png";
        ImageIcon icon = new ImageIcon(WelcomePage.class.getResource(path));
        Image image = icon.getImage(); // transform it
        Image newImg = image.getScaledInstance(1000, 700,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImg);  // transform it back
        imgLabel = new JLabel(icon);
        imgLabel.setBackground(Color.white);
        container.add(imgLabel, BorderLayout.CENTER);


        // Init Progress Bar
        JProgressBar fancyPB = new JProgressBar();
        fancyPB.setUI(new FancyProgressBar());

        container.add(fancyPB, BorderLayout.SOUTH);


        // additional settings for window frame.
        Dimension d = new Dimension(900,700);
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
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                    dispose();
                }
            }
        });
        timer.start();
    }

}