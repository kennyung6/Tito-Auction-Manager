import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class FlashScreenPanel extends JPanel {
    public static final String LOADING = "Loading...";
    public static final String PLEASE_WAIT = "Please Wait...";
    public static final String LOADING_POLICE_STATION = "Loading Police Station...";
    public static final String ALMOST_DONE = "Almost Done...";
    public static final String DONE = "Done";
    private static final int TIMER_DELAY = 50;


    private JProgressBar jb = new JProgressBar(0, 2000);
    private JLabel statusLabel = new JLabel("", SwingConstants.CENTER);

    public FlashScreenPanel() {
        setPreferredSize(new Dimension(800, 400));

        statusLabel.setForeground(Color.CYAN);
        statusLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
        jb.setStringPainted(true);
        JPanel bottomPanel = new JPanel(new BorderLayout(20, 20));
        bottomPanel.add(jb, BorderLayout.PAGE_START);
        bottomPanel.add(statusLabel, BorderLayout.CENTER);

        int eb = 40;
        setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
        setLayout(new GridLayout(0, 1));
        add(new JLabel()); // dummy component to move prog bar lower
        add(bottomPanel);
    }

    public void startProgress() {
        statusLabel.setText(LOADING);
        new Timer(TIMER_DELAY, new ActionListener() {
            private int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                i += 20;
                jb.setValue(i);

                if (i == 1800) {
                    ((Timer) e.getSource()).stop();
                    Window win = SwingUtilities.getWindowAncestor(FlashScreenPanel.this);
                    win.dispose();
                }
            }
        }).start();
    }
}
