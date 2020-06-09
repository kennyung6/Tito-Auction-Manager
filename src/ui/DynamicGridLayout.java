package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class DynamicGridLayout {


    public JPanel controls = new JPanel() {
        @Override
        public Dimension getMinimumSize() {
            return new Dimension(400, 300);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(690, 600);
        }

        @Override
        public Dimension getMaximumSize() {
            return new Dimension(690, 720);
        }
    };


    DynamicGridLayout() {
        initUI();
    }

    public final void initUI() {
        controls.setBackground(Color.WHITE);
        controls.setBorder(new TitledBorder("Child Panel"));
    }

    public JComponent getUI() {
        return controls;
    }

    public void addLabel() {
        JPanel controls1 = new JPanel(new GridLayout(3, 0, 3, 3));
        controls1.setBackground(Color.green);
        controls1.setBorder(new EmptyBorder(75, 75, 75, 75));
        controls.add(controls1);
    }




}