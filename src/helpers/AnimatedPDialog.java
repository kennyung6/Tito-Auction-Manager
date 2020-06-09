package helpers;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AnimatedPDialog {

    public static class ProgressDialog extends JDialog {


        public ProgressDialog(Component parent, String message, String path) {

            ((JComponent)getContentPane()).setBorder(new EmptyBorder(8, 8, 8, 8));

            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setSize(300, 130);

            int width = 20;
            int height = 20;

            setLayout(new BorderLayout());
            ImageIcon ii = new ImageIcon(getClass().getResource(path));
            ii.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
            JLabel imageLabel = new JLabel();
            imageLabel.setIcon(ii);
            imageLabel.setBorder(new EmptyBorder(0,0,0,5));


            // TEXT LABEL
            JLabel txtLabel = new JLabel();
            txtLabel.setText(message);
            txtLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            txtLabel.setBorder(new EmptyBorder(0,30,0,0));

            add(BorderLayout.CENTER, txtLabel);
            add(BorderLayout.EAST,imageLabel);

           setLocationRelativeTo(parent);

        }
    }


}
